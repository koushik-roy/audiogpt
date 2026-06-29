package com.koushik.audiogpt.repository;

import com.koushik.audiogpt.entity.SpeakerRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RatingRepository extends JpaRepository<SpeakerRating, Long> {

    @Query("SELECT r FROM SpeakerRating r WHERE r.speaker.id IN :speakerIds")
    List<SpeakerRating> findBySpeakerIds(List<Long> speakerIds);

    Optional<SpeakerRating> findBySpeakerId(Long speakerId);

}
