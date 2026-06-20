package com.koushik.audiogpt.service;

import com.koushik.audiogpt.dto.SpeakerRatingDTO;
import com.koushik.audiogpt.dto.SpeakerDTO;
import com.koushik.audiogpt.mapper.RatingMapper;
import com.koushik.audiogpt.mapper.SpeakerMapper;
import com.koushik.audiogpt.repository.RatingRepository;
import com.koushik.audiogpt.repository.SpeakerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SpeakerService {
    private final SpeakerRepository speakerRepository;
    private final RatingRepository ratingRepository;
    private final SpeakerMapper speakerMapper;
    private final RatingMapper ratingMapper;

    public List<SpeakerDTO> getSpeakers() {
        return speakerRepository.findAll()
                .stream()
                .map(speakerMapper::toDTO)
                .toList();
    }

    public Optional<SpeakerDTO> getSpeakerById(Long id) {
        return speakerRepository.findById(id)
                .map(speakerMapper::toDTO);
    }

    public List<SpeakerDTO> getSpeakersByBudget(Integer budget) {
        return speakerRepository.findByPriceInrLessThanEqual(budget)
                .stream()
                .limit(5)
                .map(speakerMapper::toDTO)
                .toList();
    }

    public List<SpeakerRatingDTO> getRatingsForSpeakers(List<SpeakerDTO> candidates) {
        List<Long> candidateIds = candidates.stream()
                .map(SpeakerDTO::id)
                .toList();
        return ratingRepository.findBySpeakerIds(candidateIds)
                .stream()
                .map(ratingMapper::toDTO)
                .toList();
    }
}
