package com.koushik.audiogpt.entity;


import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "recommendation_history")
public class RecommendationHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String currentSpeaker;

    private Integer budget;

    private String usage;

    private LocalDateTime createdAt;
}
