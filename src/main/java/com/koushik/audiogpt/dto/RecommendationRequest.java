package com.koushik.audiogpt.dto;

public record RecommendationRequest(
        int budget,
        String usage,
        String spaceArea,
        String currentSpeaker,
        String likes,
        String dislikes) {
}

/*
example Request
    JSON:
        {
        "budget": 15000,
        "usage": "music and gaming",
        "roomSize": "small bedroom",
        "currentSpeaker": "JBL Flip 6",
        "likes": "strong bass",
        "dislikes": "harsh treble"
        }
 */