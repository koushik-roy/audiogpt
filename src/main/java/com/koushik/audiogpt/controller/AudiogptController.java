package com.koushik.audiogpt.controller;

import com.koushik.audiogpt.dto.ComparisonRequest;
import com.koushik.audiogpt.dto.ComparisonResponse;
import com.koushik.audiogpt.dto.RecommendationRequest;
import com.koushik.audiogpt.dto.RecommendationResponse;
import com.koushik.audiogpt.service.ComparisonService;
import com.koushik.audiogpt.service.RecommendationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
    public RecommendationResponse recommend(@RequestBody RecommendationRequest request) {
        return ResponseEntity.ok(recommendationService.recommend(request)).getBody();
    }

    @PostMapping("/compare")
    public ComparisonResponse compare(@RequestBody ComparisonRequest request) {
        return ResponseEntity.ok(comparisonService.compare(request)).getBody();
    }
}
