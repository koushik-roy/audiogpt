package com.koushik.audiogpt.service;

import com.koushik.audiogpt.dto.ComparisonRequest;
import com.koushik.audiogpt.dto.ComparisonResponse;
import com.koushik.audiogpt.prompt.ComparisonPrompt;
import com.koushik.audiogpt.prompt.PromptProvider;
import com.koushik.audiogpt.prompt.PromptType;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ComparisonService {

    private final PromptProvider promptProvider;
    private final ChatClient chatClient;

    public ComparisonResponse compare(ComparisonRequest request) {

        ComparisonPrompt prompt = new ComparisonPrompt(
                request.speaker1(),
                request.speaker2()
        );

        String renderedPrompt = promptProvider.getPrompt(
                PromptType.COMPARE_SPEAKERS,
                prompt
        );

        ComparisonResponse response = chatClient.prompt()
                .user(renderedPrompt)
                .call()
                .entity(ComparisonResponse.class);

        return response;
    }
}

//TODO: Map response to ComparisonResponse DTO
