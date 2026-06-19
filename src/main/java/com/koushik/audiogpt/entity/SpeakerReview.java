package com.koushik.audiogpt.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class SpeakerReview {

    @Id
    private Long id;

    @ManyToOne
    private Speaker speaker;

    private String source;

    @Column(columnDefinition = "TEXT")
    private String reviewText;
}