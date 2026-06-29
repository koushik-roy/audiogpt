package com.koushik.audiogpt.controller;

import com.koushik.audiogpt.dto.*;
import com.koushik.audiogpt.service.ComparisonEngine;
import com.koushik.audiogpt.service.RecommendationEngine;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v2")
@RequiredArgsConstructor
public class AudiogptControllerV1 {

    private final RecommendationEngine recommendationEngine;
    private final ComparisonEngine comparisonEngine;

    @PostMapping("/recommendations")
    public ResponseEntity<RecommendationResponse> getRecommendations(@RequestBody RecommendationRequest request) {
        return ResponseEntity.ok(recommendationEngine.recommend(request));
    }

    @PostMapping("/comparisons")
    public ResponseEntity<ComparisonResponse> compareSpeakers(@RequestBody ComparisonRequest request) {
        return ResponseEntity.ok(comparisonEngine.compare(request));
    }
}
