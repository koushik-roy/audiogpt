package com.koushik.audiogpt.util;

import com.koushik.audiogpt.dto.SpeakerDTO;
import com.koushik.audiogpt.dto.SpeakerDocument;
import com.koushik.audiogpt.dto.SpeakerRatingDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SpeakerDocumentBuilder {

    public SpeakerDocument build(
            SpeakerDTO speaker,
            SpeakerRatingDTO rating) {

        String content = """
                Brand: %s
                
                Model: %s
                
                Category: %s
                
                Description:
                %s
                
                Connectivity:
                %s
                
                Bluetooth:
                %s
                
                Active Speaker:
                %s
                
                Price:
                ₹%d
                
                Audio Characteristics
                
                Bass: %d/10
                
                Midrange: %d/10
                
                Treble: %d/10
                
                Sound Quality: %d/10
                
                Build Quality: %d/10
                
                Value For Money: %d/10
                """.formatted(
                speaker.brand(),
                speaker.model(),
                speaker.category(),
                speaker.description(),
                speaker.connectivity(),
                speaker.bluetooth(),
                speaker.active(),
                speaker.priceInr(),

                rating.bassScore(),
                rating.midsScore(),
                rating.highsScore(),
                rating.soundQualityScore(),
                rating.buildQualityScore(),
                rating.valueForMoneyScore()
        );

        return new SpeakerDocument(
                speaker.id(),
                content
        );
    }

}
