package com.koushik.audiogpt.util;

import com.koushik.audiogpt.dto.SpeakerDTO;
import com.koushik.audiogpt.dto.SpeakerDocument;
import com.koushik.audiogpt.dto.SpeakerRatingDTO;
import com.koushik.audiogpt.service.SpeakerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor

public class EmbeddingIndexer {

    private final SpeakerService speakerService;
    private final SpeakerDocumentBuilder documentBuilder;
    private final VectorStore vectorStore;

    public void indexAll() {
        speakerService.getSpeakers()
                .forEach(this::indexSpeaker);
    }

    public void indexSpeaker(SpeakerDTO speaker) {
        SpeakerRatingDTO rating = speakerService.getRatingsForSpeaker(speaker);
        SpeakerDocument speakerDocument = documentBuilder.build(speaker, rating);

        Map<String, Object> metadata = new HashMap<>();

        metadata.put("speakerId", speaker.id());
        metadata.put("brand", speaker.brand());
        metadata.put("model", speaker.model());
        metadata.put("price", speaker.priceInr());
        metadata.put("category", speaker.category());
        metadata.put("bluetooth", speaker.bluetooth());
        metadata.put("active", speaker.active());

        Document document = new Document(
                UUID.nameUUIDFromBytes(speaker.id().toString().getBytes()).toString(),
                speakerDocument.content(),
                metadata
        );
        vectorStore.add(List.of(document));
    }
}