package com.koushik.audiogpt.controller;

import com.koushik.audiogpt.dto.*;
import com.koushik.audiogpt.service.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v2")
@RequiredArgsConstructor
@Tag(name = "AudioGPT Speaker API", description = "APIs for speaker recommendations, comparisons, and semantic search")
public class AudiogptControllerV1 {

    private final RecommendationEngine recommendationEngine;
    private final ComparisonEngine comparisonEngine;
    private final EmbeddingIndexer embeddingIndexer;
    private final SemanticSearchService semanticSearchService;

    @PostMapping("/recommendations")
    @Operation(
            summary = "Get speaker recommendations",
            description = "Get AI-powered speaker recommendations based on budget, usage, and preferences"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Recommendations retrieved successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = RecommendationResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request parameters"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<RecommendationResponse> getRecommendations(
            @org.springframework.web.bind.annotation.RequestBody RecommendationRequest request) {
        return ResponseEntity.ok(recommendationEngine.recommend(request));
    }

    @PostMapping("/comparisons")
    @Operation(
            summary = "Compare two speakers",
            description = "Compare specifications and features of two speaker models"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Comparison completed successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ComparisonResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid speaker names"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<ComparisonResponse> compareSpeakers(
            @org.springframework.web.bind.annotation.RequestBody ComparisonRequest request) {
        return ResponseEntity.ok(comparisonEngine.compare(request));
    }

    @PostMapping("/embeddings")
    @Operation(
            summary = "Index all speakers for semantic search",
            description = "Generate and store embeddings for all speakers to enable semantic search functionality"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Embeddings indexed successfully"),
            @ApiResponse(responseCode = "500", description = "Failed to index embeddings")
    })
    public void doEmbedding() {
        embeddingIndexer.indexAll();
    }

    @GetMapping("/search")
    @Operation(
            summary = "Semantic search for speakers",
            description = "Search for speakers using natural language queries (e.g., 'good bass under 20000')"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Search results retrieved successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = SpeakerDTO.class))),
            @ApiResponse(responseCode = "400", description = "Query parameter is required"),
            @ApiResponse(responseCode = "500", description = "Search failed")
    })
    public ResponseEntity<List<SpeakerDTO>> search(
            @RequestParam(name = "query", required = true)
            @io.swagger.v3.oas.annotations.Parameter(
                    name = "query",
                    description = "Natural language search query (e.g., 'good bass under 20000')",
                    example = "good bass under 20000",
                    required = true
            )
            String query) {
        return ResponseEntity.ok(semanticSearchService.search(query));
    }
}