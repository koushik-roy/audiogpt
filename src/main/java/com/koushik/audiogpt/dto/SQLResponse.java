package com.koushik.audiogpt.dto;

public record SQLResponse(
        String name,
        Integer bass,
        Integer mids,
        Integer highs,
        Integer buildQuality
) {
}
