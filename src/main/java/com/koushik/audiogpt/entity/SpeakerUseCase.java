package com.koushik.audiogpt.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "speaker_use_case")
public class SpeakerUseCase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Speaker speaker;

    @ManyToOne
    private UseCase useCase;
}
