package com.koushik.audiogpt.mapper;

import com.koushik.audiogpt.dto.SpeakerDTO;
import org.springframework.stereotype.Component;

@Component
public class SpeakerMapper {
    public SpeakerDTO toDTO(com.koushik.audiogpt.entity.Speaker speaker) {
        return new SpeakerDTO(
                speaker.getId(),
                speaker.getBrand() + " " + speaker.getModel(),
                speaker.getDescription(),
                speaker.getBrand(),
                speaker.getModel(),
                speaker.getPriceInr(),
                speaker.getCategory(),
                speaker.getBluetooth(),
                speaker.getActive(),
                speaker.getConnectivity()
        );
    }
}
