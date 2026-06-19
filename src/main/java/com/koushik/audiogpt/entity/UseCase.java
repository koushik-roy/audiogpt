package com.koushik.audiogpt.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "use_case")
public class UseCase {
    @Id
    private Long id;

    private String name;
}
