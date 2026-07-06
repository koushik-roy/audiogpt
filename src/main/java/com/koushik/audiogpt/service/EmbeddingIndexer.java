package com.koushik.audiogpt.service;

import com.koushik.audiogpt.dto.SpeakerDTO;
import com.koushik.audiogpt.dto.SpeakerDocument;
import com.koushik.audiogpt.dto.SpeakerRatingDTO;
import com.koushik.audiogpt.entity.Speaker;
import com.koushik.audiogpt.entity.SpeakerEmbedding;
import com.koushik.audiogpt.repository.SpeakerEmbeddingRepository;
import com.koushik.audiogpt.repository.SpeakerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor

public class EmbeddingIndexer {

    private final SpeakerService speakerService;
    private final SpeakerDocumentBuilder documentBuilder;
    private final EmbeddingService embeddingService;
    private final SpeakerEmbeddingRepository repository;
    private final SpeakerRepository speakerRepository;
    private final VectorStore vectorStore;

    public void indexAll() {
        speakerService.getSpeakers()
                .forEach(this::indexSpeaker);
    }

    public void saveToDB(SpeakerDTO speaker, SpeakerDocument speakerDocument, float[] values) {
        SpeakerEmbedding embedding = repository.findBySpeaker_Id(speaker.id()).orElse(new SpeakerEmbedding());
        embedding.setContent(speakerDocument.content());
        embedding.setEmbedding(values);
        Speaker speakerEntity = speakerRepository.findById(speaker.id())
                .orElseThrow();
        embedding.setSpeaker(speakerEntity);

        repository.save(embedding);



    }

    public void indexSpeaker(SpeakerDTO speaker) {
        SpeakerRatingDTO rating = speakerService.getRatingsForSpeaker(speaker);
        SpeakerDocument document = documentBuilder.build(speaker, rating);

        float[] embedding = embeddingService.embed(document.content());

        saveToDB(speaker, document, embedding);
        log.info("{} saved successfully:", speaker.name());
    }

    public List<String> testSearch() {
        String query = "warm sounding speakers";
        float[] queryEmbedding = embeddingService.embed(query);
        List<SpeakerEmbedding> speakers = repository.semanticSearch(Arrays.toString(queryEmbedding), 5);
        return getSpeakerNames(speakers);
    }

    private List<String> getSpeakerNames(List<SpeakerEmbedding> embeddings) {
        return embeddings.stream()
                .map(embedding -> embedding.getSpeaker().getBrand() + " " + embedding.getSpeaker().getModel())
                .toList();
    }

}