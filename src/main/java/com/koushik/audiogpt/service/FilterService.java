package com.koushik.audiogpt.service;

import com.koushik.audiogpt.dto.RecommendationRequest;
import com.koushik.audiogpt.dto.SpeakerDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class FilterService {

    public List<SpeakerDTO> filter(List<SpeakerDTO> speakers, RecommendationRequest request) {

        return speakers.stream()
                .filter(s -> withinBudget(s, request))
                .filter(s -> bluetoothMatches(s, request))
                .filter(s -> connectivityMatches(s, request))
                .toList();
    }

    private boolean withinBudget(SpeakerDTO speaker, RecommendationRequest request) {
        return request.budget() == null || speaker.priceInr() <= request.budget();
    }

    private boolean bluetoothMatches(SpeakerDTO speaker, RecommendationRequest request) {

        if (request.bluetooth() == null) {
            return true;
        }

        return speaker.bluetooth().equals(request.bluetooth());
    }


    private boolean connectivityMatches(SpeakerDTO speaker, RecommendationRequest request) {

        if (request.connectivity() == null || request.connectivity().isBlank()) {
            return true;
        }

        return speaker.connectivity()
                .toLowerCase()
                .contains(request.connectivity().toLowerCase());
    }
}