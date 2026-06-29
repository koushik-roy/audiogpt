package com.koushik.audiogpt.prompt;

import com.koushik.audiogpt.dto.ComparisonMetrics;
import com.koushik.audiogpt.dto.SpeakerMetrics;

import java.util.Map;

public record ComparisonPromptV1(
        SpeakerMetrics speaker1,
        SpeakerMetrics speaker2,
        ComparisonMetrics metrics
) implements PromptModel {
    @Override
    public Map<String, Object> variables() {
        return Map.of(
                "speaker1", speaker1,
                "speaker2", speaker2,
                "metrics", metrics
        );
    }
}
