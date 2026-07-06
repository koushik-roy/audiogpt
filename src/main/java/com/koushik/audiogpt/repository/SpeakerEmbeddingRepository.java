package com.koushik.audiogpt.repository;

import com.koushik.audiogpt.entity.SpeakerEmbedding;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SpeakerEmbeddingRepository extends JpaRepository<SpeakerEmbedding, Long> {

    public Optional<SpeakerEmbedding> findBySpeaker_Id(Long speakerId);

    @Query(value = """
            SELECT * FROM speaker_embedding
            ORDER BY embedding <=> CAST(:embedding AS vector)
            LIMIT :limit
            """,
            nativeQuery = true)
    List<SpeakerEmbedding> semanticSearch(
            @Param("embedding") String embedding,
            @Param("limit") Integer limit);
}
