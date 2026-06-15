package com.koushik.audiogpt.service;

import com.koushik.audiogpt.dto.RecommendationRequest;
import com.koushik.audiogpt.prompt.PromptProvider;
import com.koushik.audiogpt.prompt.PromptType;
import com.koushik.audiogpt.prompt.RecommendationPrompt;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RecommendationService {

    private final ChatClient chatClient;
    private final PromptProvider promptProvider;

    //    public RecommendationResponse recommend(RecommendationRequest request) {
    public String recommend(RecommendationRequest request) {

        RecommendationPrompt prompt =
                new RecommendationPrompt(
                        request.budget(),
                        request.usage(),
                        request.spaceArea(),
                        request.currentSpeaker(),
                        request.likes(),
                        request.dislikes()
                );

        String renderedPrompt =
                promptProvider.getPrompt(
                        PromptType.RECOMMEND_SPEAKER,
                        prompt
                );

        String response = chatClient.prompt()
                .user(renderedPrompt)
                .call()
                .content();

        return response;
    }
}

//TODO: Map response to RecommendationResponse DTO