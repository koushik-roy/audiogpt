package com.koushik.audiogpt.service;

import com.koushik.audiogpt.dto.*;
import com.koushik.audiogpt.prompt.PromptProvider;
import com.koushik.audiogpt.prompt.PromptType;
import com.koushik.audiogpt.prompt.RecommendationPromptv1;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class RecommendationServiceV1 {

    private final SpeakerService speakerService;
    private final ChatClient chatClient;
    private final PromptProvider promptProvider;

    public RecommendationResponse recommend(RecommendationRequest request) {

        List<SpeakerDTO> candidates =
                speakerService.getSpeakersByBudget(
                        request.budget()
                );

        log.debug("Found {} candidates for budget {}", candidates.size(), request.budget());

        List<SpeakerRatingDTO> ratings =
                speakerService.getRatingsForSpeakers(
                        candidates
                );

        Map<Long, SpeakerRatingDTO> ratingsBySpeakerId =
                ratings.stream()
                        .collect(Collectors.toMap(
                                SpeakerRatingDTO::speakerId,
                                Function.identity()
                        ));

        log.debug("All ratings for {} candidates", ratingsBySpeakerId.values());

        List<SpeakerDTO> topCandidates =
                candidates.stream()
                        .sorted(Comparator.comparingInt(
                                (SpeakerDTO speaker) -> calculateScore(
                                        ratingsBySpeakerId.get(
                                                speaker.id()
                                        ),
                                        request
                                )
                        ).reversed())
                        .limit(5)
                        .toList();

        log.debug("Top candidates: {}", topCandidates);

        String availableSpeakers =
                formatAvailableSpeakers(
                        topCandidates,
                        ratingsBySpeakerId
                );

        String prompt = buildPrompt(
                request,
                availableSpeakers
        );

        log.debug("Constructed prompt for LLM:\n{}", prompt);

        return chatClient.prompt()
                .user(prompt)
                .call()
                .entity(RecommendationResponse.class);


    }

    private int calculateScore(SpeakerRatingDTO rating, RecommendationRequest request) {
        int score = 0;
        score += rating.soundQualityScore() +
                rating.buildQualityScore() +
                rating.valueForMoneyScore();

        String likes =
                Optional.ofNullable(request.likes())
                        .orElse("")
                        .toLowerCase();

        if (likes.contains("bass") || likes.contains("low")) {
            score += rating.bassScore() * 3;
        }
        if (likes.contains("treble") || likes.contains("highs")) {
            score += rating.highsScore() * 3;
        }
        if (likes.contains("midrange") || likes.contains("mids") || likes.contains("vocals")) {
            score += rating.midsScore() * 3;
        }
        //log.debug("Calculated score for speaker {}: {}", rating.speakerId(), score);
        return score;
    }

    private String formatAvailableSpeakers(List<SpeakerDTO> speakers, Map<Long, SpeakerRatingDTO> ratingsBySpeakerId) {
        StringBuilder sb =
                new StringBuilder();

        for (SpeakerDTO speaker : speakers) {

            SpeakerRatingDTO rating =
                    ratingsBySpeakerId.get(
                            speaker.id()
                    );

            sb.append(speaker.brand())
                    .append(" ")
                    .append(speaker.model())
                    .append("\n");

            if (rating != null) {

                sb.append("Bass: ")
                        .append(rating.bassScore())
                        .append("\n");

                sb.append("Mids: ")
                        .append(rating.midsScore())
                        .append("\n");

                sb.append("Highs: ")
                        .append(rating.highsScore())
                        .append("\n");

                sb.append("Sound Quality: ")
                        .append(rating.soundQualityScore())
                        .append("\n");

                sb.append("Value For Money: ")
                        .append(rating.valueForMoneyScore())
                        .append("\n");
            }

            sb.append("\n");
        }

        return sb.toString();
    }

    private String buildPrompt(
            RecommendationRequest recommendationRequest, String availableSpeakers) {

        RecommendationPromptv1 prompt =
                new RecommendationPromptv1(
                        recommendationRequest.budget(),
                        recommendationRequest.usage(),
                        recommendationRequest.currentSpeaker(),
                        recommendationRequest.likes(),
                        recommendationRequest.dislikes(),
                        availableSpeakers
                );

        return promptProvider.getPrompt(
                PromptType.RECOMMEND_SPEAKER_2,
                prompt
        );
    }
}
