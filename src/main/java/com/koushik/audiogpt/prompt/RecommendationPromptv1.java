package com.koushik.audiogpt.prompt;

import java.util.Map;

public record RecommendationPromptv1(
        Integer budget,
        String usage,
        String currentSpeaker,
        String likes,
        String dislikes,
        String availableSpeakers
) implements PromptModel {
    @Override
    public Map<String, Object> variables() {
        return Map.of(
                "budget", budget,
                "usage", usage,
                "currentSpeaker", currentSpeaker,
                "likes", likes,
                "dislikes", dislikes,
                "availableSpeakers", availableSpeakers
        );
    }
}
