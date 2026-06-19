package com.koushik.audiogpt.service;

import com.koushik.audiogpt.dto.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class RecommendationServiceV1 {

    private final SpeakerService speakerService;
    private final ChatClient chatClient;

    public RecommendationResponse recommend(RecommendationRequest request) {

        List<SpeakerDTO> candidates = speakerService.getSpeakersByBudget(request.budget());

        String candidatesSpeakers = formatAvailableSpeakers(candidates);

        String prompt = "Recommend me the best one among: \n" + candidatesSpeakers;

        RecommendationResponse response =
                chatClient.prompt()
                        .user(prompt)
                        .call()
                        .entity(RecommendationResponse.class);

        return response;

    }

    private String formatAvailableSpeakers(List<SpeakerDTO> speakers) {
        List<SpeakerRatingDTO> ratings = speakerService.getRatingsForSpeakers(speakers);
        Map<Long, SpeakerRatingDTO> byId = ratings.stream()
                .collect(Collectors.toMap(SpeakerRatingDTO::speakerId, r -> r));
        StringBuilder sb = new StringBuilder("Available Speakers:\n\n");
        for (SpeakerDTO s : speakers) {
            sb.append(s.brand()).append("-").append(s.model()).append("\n");
            SpeakerRatingDTO r = byId.get(s.id());
            if (r != null) {
                sb.append("Bass: ").append(r.bassScore()).append("\n")
                        .append("Mids: ").append(r.midsScore()).append("\n")
                        .append("Highs: ").append(r.highsScore()).append("\n\n");
            } else {
                sb.append("Bass: N/A\nMids: N/A\nHighs: N/A\n\n");
            }
        }
        return sb.toString().trim();
    }

}
