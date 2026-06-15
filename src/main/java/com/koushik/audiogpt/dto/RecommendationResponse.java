package com.koushik.audiogpt.dto;

import java.util.List;

public record RecommendationResponse(
        List<SpeakerRecommendation> recommendations) {
}
