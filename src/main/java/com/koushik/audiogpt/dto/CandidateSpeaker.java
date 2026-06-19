package com.koushik.audiogpt.dto;

import java.util.List;

public record CandidateSpeaker(
        List<SQLResponse> recommendations) {
}
