package com.koushik.audiogpt.dto;

public record RankedSpeaker(
        SpeakerDTO speaker,
        SpeakerRatingDTO rating,
        Integer score
) {
}
