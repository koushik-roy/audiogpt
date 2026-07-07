package com.koushik.audiogpt.dto;

import jakarta.validation.constraints.NotNull;

public record RecommendationRequest(
        @NotNull Integer budget,
        String usage,
        String spaceArea,
        String currentSpeaker,
        Boolean bluetooth,
        String connectivity,
        String likes) {
}

/*
example Request
    JSON:
        {
        "budget": 15000,
        "usage": "music and gaming",
        "roomSize": "small bedroom",
        "currentSpeaker": "JBL Flip 6",
        "likes": "strong bass"
        }
 */