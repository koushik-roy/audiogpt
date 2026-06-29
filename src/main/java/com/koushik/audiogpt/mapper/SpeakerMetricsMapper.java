package com.koushik.audiogpt.mapper;

import com.koushik.audiogpt.dto.SpeakerDTO;
import com.koushik.audiogpt.dto.SpeakerMetrics;
import com.koushik.audiogpt.dto.SpeakerRatingDTO;
import org.springframework.stereotype.Component;

@Component
public class SpeakerMetricsMapper {

    public SpeakerMetrics toMetrics(SpeakerDTO speakerDTO,
                                    SpeakerRatingDTO speakerRatingDTO,
                                    int totalScore) {
        return new SpeakerMetrics(
                speakerDTO.brand(),
                speakerDTO.name(),
                speakerRatingDTO.bassScore(),
                speakerRatingDTO.highsScore(),
                speakerRatingDTO.midsScore(),
                speakerRatingDTO.soundQualityScore(),
                speakerRatingDTO.buildQualityScore(),
                speakerRatingDTO.valueForMoneyScore(),
                totalScore
        );
    }
}
