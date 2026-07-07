package com.koushik.audiogpt.controller;

import com.koushik.audiogpt.dto.*;
import com.koushik.audiogpt.service.ComparisonService;
import com.koushik.audiogpt.service.RecommendationService;
import com.koushik.audiogpt.service.RecommendationEngine;
import com.koushik.audiogpt.service.SpeakerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Tag(name = "AudioGPT Speaker API V1", description = "Legacy APIs for speaker recommendations, comparisons, and retrieval using pure LLM")
public class AudiogptController {

    private final ComparisonService comparisonService;
    private final RecommendationService recommendationService;
    private final SpeakerService speakerService;
    private final RecommendationEngine recommendationEngine;

    @PostMapping("/recommend")
    @Operation(
            summary = "Get speaker recommendations",
            description = "Get AI-powered speaker recommendations based on budget, usage, and preferences (V1)"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Recommendations retrieved successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = RecommendationResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request parameters"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public RecommendationResponse recommend(
            @org.springframework.web.bind.annotation.RequestBody RecommendationRequest request) {
        return ResponseEntity.ok(recommendationService.recommend(request)).getBody();
    }

    @PostMapping("/compare")
    @Operation(
            summary = "Compare two speakers",
            description = "Compare specifications and features of two speaker models (V1)"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Comparison completed successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ComparisonResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid speaker names"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ComparisonResponse compare(
            @org.springframework.web.bind.annotation.RequestBody ComparisonRequest request) {
        return ResponseEntity.ok(comparisonService.compare(request)).getBody();
    }

    @GetMapping("/speakers")
    @Operation(
            summary = "Get all speakers",
            description = "Retrieve a list of all available speakers in the database"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Speakers retrieved successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = SpeakerDTO.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<SpeakerDTO>> getSpeakers() {
        return ResponseEntity.ok(speakerService.getSpeakers());
    }

    @GetMapping("/speaker/{id}")
    @Operation(
            summary = "Get speaker by ID",
            description = "Retrieve detailed information about a specific speaker"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Speaker found and retrieved successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = SpeakerDTO.class))),
            @ApiResponse(responseCode = "404", description = "Speaker not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public Optional<SpeakerDTO> getSpeakerById(
            @PathVariable(name = "id")
            @Parameter(
                    name = "id",
                    description = "The unique identifier of the speaker",
                    example = "1",
                    required = true
            )
            Long id) {
        return speakerService.getSpeakerById(id);
    }

}