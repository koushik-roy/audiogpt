package com.koushik.audiogpt.dto;

public record SpeakerAnalysis(
        String name,
        AudioProfile audioProfile,
        int soundQualityScore,
        int buildQualityScore,
        int valueForMoneyScore,
        String bestUseCases,
        String summary
) {
}