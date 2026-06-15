package com.koushik.audiogpt.dto;

public record ComparisonResponse(
        SpeakerAnalysis speaker1,
        SpeakerAnalysis speaker2,
        WinnerRecommendation recommendation
) {
}