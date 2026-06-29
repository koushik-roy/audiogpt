package com.koushik.audiogpt.service;

import com.koushik.audiogpt.dto.*;
import com.koushik.audiogpt.prompt.PromptProvider;
import com.koushik.audiogpt.prompt.PromptType;
import com.koushik.audiogpt.prompt.RecommendationPromptV1;
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
public class RecommendationEngine {

    private final SpeakerService speakerService;
    private final ChatClient chatClient;
    private final PromptProvider promptProvider;

    public RecommendationResponse recommend(RecommendationRequest request) {

        List<SpeakerDTO> candidates = speakerService.getSpeakersByBudget(request.budget());

        log.debug("Found {} candidates for budget {}", candidates.size(), request.budget());

        List<SpeakerRatingDTO> ratings = speakerService.getRatingsForSpeakers(candidates);

        Map<Long, SpeakerRatingDTO> ratingsBySpeakerId = ratings.stream().collect(Collectors.toMap(SpeakerRatingDTO::speakerId, Function.identity()));

        log.debug("All ratings for {} candidates", ratingsBySpeakerId.values());

        List<SpeakerDTO> topCandidates = candidates.stream().sorted(Comparator.comparingInt((SpeakerDTO speaker) ->
                calculateScore(ratingsBySpeakerId.get(speaker.id()), request)).reversed()).limit(5).toList();

        List<RankedSpeaker> rankedSpeakers =
                candidates.stream()
                        .map(candidate -> {
                            SpeakerRatingDTO rating = ratingsBySpeakerId.get(candidate.id());

                            return new RankedSpeaker(candidate, rating, calculateScore(rating, request));
                        })
                        .sorted(
                                Comparator.comparingInt(
                                        RankedSpeaker::score
                                ).reversed()
                        )
                        .limit(2)
                        .toList();

        log.debug("Top candidates: {}", rankedSpeakers);

        String rankedSpeakerSummary = formatRankedSpeakers(rankedSpeakers);

        String prompt = buildPrompt(request, rankedSpeakerSummary);

        log.debug("Constructed prompt for LLM:\n{}", prompt);

        return chatClient.prompt().user(prompt).call().entity(RecommendationResponse.class);
    }

    private int calculateScore(SpeakerRatingDTO rating, RecommendationRequest request) {
        int score = 0;
        score += rating.soundQualityScore() + rating.buildQualityScore() + rating.valueForMoneyScore();

        String likes = Optional.ofNullable(request.likes()).orElse("").toLowerCase();

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

    private String formatRankedSpeakers(
            List<RankedSpeaker> rankedSpeakers) {

        StringBuilder sb = new StringBuilder();

        int rank = 1;

        for (RankedSpeaker ranked : rankedSpeakers) {

            SpeakerDTO speaker = ranked.speaker();
            SpeakerRatingDTO rating = ranked.rating();

            sb.append("Rank #")
                    .append(rank++)
                    .append("\n");

            sb.append("Speaker: ")
                    .append(speaker.brand())
                    .append(" ")
                    .append(speaker.model())
                    .append("\n");

            sb.append("Recommendation Score: ")
                    .append(ranked.score())
                    .append("\n");

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

            sb.append("Build Quality: ")
                    .append(rating.buildQualityScore())
                    .append("\n");

            sb.append("Value For Money: ")
                    .append(rating.valueForMoneyScore())
                    .append("\n\n");
        }

        return sb.toString();
    }

    private String buildPrompt(RecommendationRequest recommendationRequest, String rankedSpeakers) {

        RecommendationPromptV1 prompt = new RecommendationPromptV1(
                recommendationRequest.currentSpeaker(),
                recommendationRequest.likes(),
                recommendationRequest.dislikes(),
                rankedSpeakers);

        return promptProvider.getPrompt(PromptType.RECOMMEND_SPEAKER_3, prompt);
    }
}
