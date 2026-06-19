package com.koushik.audiogpt.dto;

public record SpeakerRatingDTO(
        Long id,
        Long speakerId,
        Integer bassScore,
        Integer midsScore,
        Integer highsScore,
        Integer soundQualityScore,
        Integer buildQualityScore,
        Integer valueForMoneyScore
) {
}
