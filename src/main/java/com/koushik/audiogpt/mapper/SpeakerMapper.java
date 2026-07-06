package com.koushik.audiogpt.mapper;

import com.koushik.audiogpt.dto.SpeakerDTO;
import com.koushik.audiogpt.entity.Speaker;
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

    public Speaker toEntity(SpeakerDTO dto) {
        return Speaker.builder()
                .id(dto.id())
                .brand(dto.brand())
                .model(dto.model())
                .priceInr(dto.priceInr())
                .category(dto.category())
                .bluetooth(dto.bluetooth())
                .active(dto.active())
                .connectivity(dto.connectivity())
                .description(dto.description())
                .build();

    }
}
