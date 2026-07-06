package com.koushik.audiogpt.dto;

public record SpeakerDTO(
        Long id,
        String name,
        String description,
        String brand,
        String model,
        Integer priceInr,
        String category,
        Boolean bluetooth,
        Boolean active,
        String connectivity
) {

    public String createName(String brand, String model) {
        return brand + model;
    }
}
