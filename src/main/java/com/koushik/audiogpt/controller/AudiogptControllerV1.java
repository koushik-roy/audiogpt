package com.koushik.audiogpt.controller;

import com.koushik.audiogpt.dto.*;
import com.koushik.audiogpt.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v2")
@RequiredArgsConstructor
public class AudiogptControllerV1 {

    private final RecommendationEngine recommendationEngine;
    private final ComparisonEngine comparisonEngine;
    private final EmbeddingIndexer embeddingIndexer;
    private final SemanticSearchService semanticSearchService;

    @PostMapping("/recommendations")
    public ResponseEntity<RecommendationResponse> getRecommendations(@RequestBody RecommendationRequest request) {
        return ResponseEntity.ok(recommendationEngine.recommend(request));
    }

    @PostMapping("/comparisons")
    public ResponseEntity<ComparisonResponse> compareSpeakers(@RequestBody ComparisonRequest request) {
        return ResponseEntity.ok(comparisonEngine.compare(request));
    }

    @PostMapping("/embeddings")
    public void doEmbedding() {
        embeddingIndexer.indexAll();
    }

    @GetMapping("/search")
    public ResponseEntity<List<SpeakerDTO>> search(@RequestParam String query) {
        return ResponseEntity.ok(semanticSearchService.search(query));
    }
}
