package com.koushik.audiogpt.service;

import com.koushik.audiogpt.dto.SpeakerDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SemanticSearchService {

    private final VectorStore vectorStore;
    private final SpeakerService speakerService;

    public List<SpeakerDTO> search(String query) {
        List<Document> documents = vectorStore.similaritySearch(
                SearchRequest.builder()
                        .query(query)
                        .topK(5)
                        .build()
        );

        List<SpeakerDTO> speakers = documents.stream()
                .map(doc -> ((Number) doc.getMetadata().get("speakerId")).longValue())
                .flatMap(id -> speakerService.getSpeakerById(id).stream())
                .toList();

        return speakers;

    }
}
