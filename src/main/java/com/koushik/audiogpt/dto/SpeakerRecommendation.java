package com.koushik.audiogpt.dto;

import java.util.List;

public record SpeakerRecommendation(
        String name,
        String reason,
        List<String> pros,
        List<String> cons
) {
}
