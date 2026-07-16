package com.koushik.audiogpt.service;

import com.koushik.audiogpt.dto.SpeakerDTO;
import com.koushik.audiogpt.dto.SpeakerKnowledge;
import com.koushik.audiogpt.dto.SpeakerRatingDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SpeakerKnowledgeService {

    public SpeakerKnowledge build(SpeakerDTO speaker, SpeakerRatingDTO rating) {

        return new SpeakerKnowledge(
                bassDescription(rating.bassScore()),
                midsDescription(rating.midsScore()),
                highsDescription(rating.highsScore()),
                soundDescription(rating.soundQualityScore()),
                buildDescription(rating.buildQualityScore()),
                valueDescription(rating.valueForMoneyScore()),
                buildKeywords(speaker, rating)
        );
    }

    private String bassDescription(int score) {

        if (score >= 9)
            return "Powerful, deep bass with excellent extension.";

        if (score >= 7)
            return "Controlled bass with good impact.";

        if (score >= 5)
            return "Balanced bass response.";

        return "Light bass focused on accuracy.";
    }

    private String midsDescription(int score) {

        if (score >= 9)
            return "Excellent vocal clarity and instrument separation.";

        if (score >= 7)
            return "Clear and natural mids.";

        if (score >= 5)
            return "Balanced midrange.";

        return "Recessed midrange.";
    }

    private String highsDescription(int score) {

        if (score >= 9)
            return "Highly detailed and airy treble.";

        if (score >= 7)
            return "Smooth highs with good detail.";

        if (score >= 5)
            return "Balanced treble.";

        return "Relaxed highs.";
    }

    private String soundDescription(int score) {

        if (score >= 9)
            return "Excellent overall sound fidelity.";

        if (score >= 7)
            return "Very enjoyable overall sound.";

        if (score >= 5)
            return "Good overall sound quality.";

        return "Average sound quality.";
    }

    private String buildDescription(int score) {

        if (score >= 9)
            return "Premium construction and excellent durability.";

        if (score >= 7)
            return "Solid build quality.";

        return "Acceptable construction.";
    }

    private String valueDescription(int score) {

        if (score >= 9)
            return "Outstanding value for the price.";

        if (score >= 7)
            return "Good value.";

        return "Average value.";
    }

    private List<String> buildKeywords(
            SpeakerDTO speaker,
            SpeakerRatingDTO rating) {

        List<String> tags = new ArrayList<>();

        tags.add(speaker.category());

        if (speaker.bluetooth())
            tags.add("Bluetooth");

        if (speaker.active())
            tags.add("Powered Speaker");

        if (rating.bassScore() >= 8)
            tags.add("Strong Bass");

        if (rating.midsScore() >= 8)
            tags.add("Excellent Vocals");

        if (rating.highsScore() >= 8)
            tags.add("Detailed Treble");

        if (rating.soundQualityScore() >= 8)
            tags.add("High Fidelity");

        if (rating.valueForMoneyScore() >= 9)
            tags.add("Excellent Value");

        return tags;
    }
}