package com.koushik.audiogpt.mapper;

import com.koushik.audiogpt.dto.SpeakerRatingDTO;
import org.springframework.stereotype.Component;

@Component
public class RatingMapper {
    public SpeakerRatingDTO toDTO(com.koushik.audiogpt.entity.SpeakerRating rating) {
        return new SpeakerRatingDTO(
                rating.getId(),
                rating.getSpeaker().getId(),
                rating.getBassScore(),
                rating.getMidsScore(),
                rating.getHighsScore(),
                rating.getSoundQualityScore(),
                rating.getBuildQualityScore(),
                rating.getValueForMoneyScore()
        );
    }
}
