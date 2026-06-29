package com.koushik.audiogpt.controller;

import com.koushik.audiogpt.dto.*;
import com.koushik.audiogpt.service.ComparisonService;
import com.koushik.audiogpt.service.RecommendationService;
import com.koushik.audiogpt.service.RecommendationEngine;
import com.koushik.audiogpt.service.SpeakerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AudiogptController {

    private final ComparisonService comparisonService;
    private final RecommendationService recommendationService;
    private final SpeakerService speakerService;
    private final RecommendationEngine recommendationEngine;

    @PostMapping("/recommend")
    public RecommendationResponse recommend(@RequestBody RecommendationRequest request) {
        return ResponseEntity.ok(recommendationService.recommend(request)).getBody();
    }

    @PostMapping("/compare")
    public ComparisonResponse compare(@RequestBody ComparisonRequest request) {
        return ResponseEntity.ok(comparisonService.compare(request)).getBody();
    }

    @GetMapping("/speakers")
    public ResponseEntity<List<SpeakerDTO>> getSpeakers() {
        return ResponseEntity.ok(speakerService.getSpeakers());
    }

    @GetMapping("/speaker/{id}")
    public Optional<SpeakerDTO> getSpeakerById(@PathVariable Long id) {
        return speakerService.getSpeakerById(id);
    }

}
