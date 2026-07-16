package com.koushik.audiogpt.service;

import com.koushik.audiogpt.dto.SpeakerDTO;
import com.koushik.audiogpt.dto.SpeakerDocument;
import com.koushik.audiogpt.dto.SpeakerKnowledge;
import com.koushik.audiogpt.dto.SpeakerRatingDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SpeakerDocumentBuilder {

    private final SpeakerKnowledgeService knowledgeService;

    public SpeakerDocument build(SpeakerDTO speaker, SpeakerRatingDTO rating) {

        SpeakerKnowledge knowledge = knowledgeService.build(speaker, rating);

        String content = """
                Brand: %s
                
                Model: %s
                
                Category: %s
                
                Description:
                %s
                
                Features
                
                Connectivity:
                %s
                
                Bluetooth:
                %s
                
                Active Speaker:
                %s
                
                Price:
                ₹%d
                
                Audio Characteristics
                
                Bass:
                %d/10
                %s
                
                Midrange:
                %d/10
                %s
                
                Treble:
                %d/10
                %s
                
                Sound Quality:
                %d/10
                %s
                
                Build Quality:
                %d/10
                %s
                
                Value For Money:
                %d/10
                %s
                
                Keywords
                
                %s
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
                knowledge.bassDescription(),

                rating.midsScore(),
                knowledge.midsDescription(),

                rating.highsScore(),
                knowledge.highsDescription(),

                rating.soundQualityScore(),
                knowledge.soundDescription(),

                rating.buildQualityScore(),
                knowledge.buildDescription(),

                rating.valueForMoneyScore(),
                knowledge.valueDescription(),

                String.join(", ", knowledge.keywords())
        );

        return new SpeakerDocument(speaker.id(), content);
    }
}
