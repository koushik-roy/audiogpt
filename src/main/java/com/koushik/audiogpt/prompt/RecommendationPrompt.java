package com.koushik.audiogpt.prompt;

import java.util.Map;

public record RecommendationPrompt(
        int budget,
        String usage,
        String spaceArea,
        String currentSpeaker,
        String likes) implements PromptModel {
    @Override
    public Map<String, Object> variables() {
        return Map.of(
                "budget", budget,
                "usage", usage,
                "spaceArea", spaceArea,
                "currentSpeaker", currentSpeaker,
                "likes", likes
        );
    }
}
