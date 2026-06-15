package com.koushik.audiogpt.controller;

import com.koushik.audiogpt.dto.ComparisonRequest;
import com.koushik.audiogpt.dto.RecommendationRequest;
import com.koushik.audiogpt.service.ComparisonService;
import com.koushik.audiogpt.service.RecommendationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/advisor")
@RequiredArgsConstructor
public class AudiogptController {

    private final ComparisonService comparisonService;
    private final RecommendationService recommendationService;

    @PostMapping("/recommend")
    public String recommend(@RequestBody RecommendationRequest request) {
        return recommendationService.recommend(request);
    }

    @PostMapping("/compare")
    public String compare(@RequestBody ComparisonRequest request) {
        return comparisonService.compare(request);
    }
}
