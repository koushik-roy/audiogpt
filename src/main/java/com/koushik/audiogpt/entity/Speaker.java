package com.koushik.audiogpt.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "speaker")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Speaker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String brand;

    private String model;

    private Integer priceInr;

    private String category;

    private Boolean bluetooth;

    private Boolean active;

    private String connectivity;

    private String description;
}

