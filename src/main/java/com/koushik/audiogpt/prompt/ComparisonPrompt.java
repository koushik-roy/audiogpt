package com.koushik.audiogpt.prompt;

public record ComparisonPrompt(
        String speaker1Name,
        String speaker2Name
) implements PromptModel {
    @Override
    public java.util.Map<String, Object> variables() {
        return java.util.Map.of(
                "speaker1Name", speaker1Name,
                "speaker2Name", speaker2Name
        );
    }
}
