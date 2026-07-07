package com.koushik.audiogpt.prompt;

import com.koushik.audiogpt.dto.RankedSpeaker;

import java.util.List;
import java.util.Map;

public record RecommendationPromptV1(
        String currentSpeaker,
        String likes,
        String rankedSpeakers
) implements PromptModel {
    @Override
    public Map<String, Object> variables() {
        return Map.of(
                "currentSpeaker", currentSpeaker,
                "likes", likes,
                "rankedSpeakers", rankedSpeakers
        );
    }
}
