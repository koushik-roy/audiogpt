package com.koushik.audiogpt.service;

import com.koushik.audiogpt.dto.*;
import com.koushik.audiogpt.mapper.SpeakerMetricsMapper;
import com.koushik.audiogpt.prompt.ComparisonPromptV1;
import com.koushik.audiogpt.prompt.PromptProvider;
import com.koushik.audiogpt.prompt.PromptType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ComparisonEngine {

    private final SpeakerService speakerService;
    private final SpeakerMetricsMapper speakerMetricsMapper;
    private final PromptProvider promptProvider;
    private final ChatClient chatClient;


    public ComparisonResponse compare(ComparisonRequest request) {
        String speaker1 = request.speaker1();
        String speaker2 = request.speaker2();

        SpeakerDTO speakerDTO1 = speakerService.findByModel(speaker1);
        SpeakerDTO speakerDTO2 = speakerService.findByModel(speaker2);

        SpeakerRatingDTO rating1 = speakerService.getRatingsForSpeaker(speakerDTO1);
        SpeakerRatingDTO rating2 = speakerService.getRatingsForSpeaker(speakerDTO2);

        SpeakerMetrics speakerMetrics1 = speakerMetricsMapper.toMetrics(speakerDTO1, rating1, totalScore(rating1));
        SpeakerMetrics speakerMetrics2 = speakerMetricsMapper.toMetrics(speakerDTO2, rating2, totalScore(rating2));

        log.debug("Speaker metrics for {}: {}\n", speakerDTO1.model(), speakerMetrics1);
        log.debug("Speaker metrics for {}: {}\n", speakerDTO2.model(), speakerMetrics2);

        ComparisonMetrics metrics = buildMetrics(speakerMetrics1, speakerMetrics2);

        log.debug("Comparison metrics: {}", metrics);

        ComparisonPromptV1 promptV1 = new ComparisonPromptV1(
                speakerMetrics1,
                speakerMetrics2,
                metrics
        );


        String prompt = promptProvider.getPrompt(PromptType.COMPARE_SPEAKERS_2, promptV1);

        log.debug("Constructed prompt for LLM: \n{}", prompt);
        return chatClient.prompt().user(prompt).call().entity(ComparisonResponse.class);
    }

    private int totalScore(SpeakerRatingDTO rating) {
        return rating.bassScore()
                + rating.midsScore()
                + rating.highsScore()
                + rating.soundQualityScore()
                + rating.buildQualityScore()
                + rating.valueForMoneyScore();
    }

    private int totalScore(SpeakerMetrics speaker) {

        return speaker.getBassScore()
                + speaker.getMidsScore()
                + speaker.getHighsScore()
                + speaker.getSoundQualityScore()
                + speaker.getBuildQualityScore()
                + speaker.getValueForMoneyScore();
    }

    private ComparisonMetrics buildMetrics(
            SpeakerMetrics s1,
            SpeakerMetrics s2) {

        int total1 =
                totalScore(s1);

        int total2 =
                totalScore(s2);

        int difference =
                Math.abs(total1 - total2);

        boolean closeComparison =
                difference <= 3;

        return new ComparisonMetrics(total1, total2,

                winner(
                        s1.getBassScore(),
                        s2.getBassScore(),
                        s1.getModel(),
                        s2.getModel()
                ),

                winner(
                        s1.getMidsScore(),
                        s2.getMidsScore(),
                        s1.getModel(),
                        s2.getModel()
                ),

                winner(
                        s1.getHighsScore(),
                        s2.getHighsScore(),
                        s1.getModel(),
                        s2.getModel()
                ),

                winner(
                        s1.getSoundQualityScore(),
                        s2.getSoundQualityScore(),
                        s1.getModel(),
                        s2.getModel()
                ),

                winner(
                        s1.getBuildQualityScore(),
                        s2.getBuildQualityScore(),
                        s1.getModel(),
                        s2.getModel()
                ),

                winner(
                        s1.getValueForMoneyScore(),
                        s2.getValueForMoneyScore(),
                        s1.getModel(),
                        s2.getModel()
                ),

                total1 >= total2
                        ? s1.getModel()
                        : s2.getModel(),

                difference,

                closeComparison
        );
    }

    private String winner(
            int score1,
            int score2,
            String speaker1,
            String speaker2) {

        if (score1 > score2) {
            return speaker1;
        }

        if (score2 > score1) {
            return speaker2;
        }

        return "Tie";
    }
}
