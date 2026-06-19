package com.koushik.audiogpt.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "speaker_rating")
@Getter
@Setter
public class SpeakerRating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Speaker speaker;

    private Integer bassScore;
    private Integer midsScore;
    private Integer highsScore;

    private Integer soundQualityScore;
    private Integer buildQualityScore;
    private Integer valueForMoneyScore;
}
