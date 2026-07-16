package com.koushik.audiogpt.dto;

import java.util.List;

public record SpeakerKnowledge(
        String bassDescription,

        String midsDescription,

        String highsDescription,

        String soundDescription,

        String buildDescription,

        String valueDescription,

        List<String> keywords
) {
}
