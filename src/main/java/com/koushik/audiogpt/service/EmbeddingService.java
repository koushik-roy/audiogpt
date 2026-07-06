package com.koushik.audiogpt.service;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.embedding.EmbeddingOptions;
import org.springframework.ai.embedding.EmbeddingRequest;
import org.springframework.ai.embedding.EmbeddingResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmbeddingService {

    private final EmbeddingModel embeddingModel;

    public float[] embed(String text) {
        EmbeddingResponse embeddingResponse = embeddingModel.call(new EmbeddingRequest(
                List.of(text),
                EmbeddingOptions.builder().build()
        ));

        //        List<Float> embedding = new ArrayList<>(vector.length);
//
//        for (float value : vector) {
//            embedding.add(value);
//        }

        return embeddingResponse.getResults().getFirst().getOutput();
    }
}
