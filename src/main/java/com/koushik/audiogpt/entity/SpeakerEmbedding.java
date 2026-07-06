package com.koushik.audiogpt.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Entity
@Table(name = "speaker_embedding")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SpeakerEmbedding {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT",
            nullable = false)
    private String content;

    @JdbcTypeCode(SqlTypes.VECTOR)
    @Column(columnDefinition = "vector(768)")
    private float[] embedding;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "speaker_id",
            nullable = false,
            unique = true)
    private Speaker speaker;
}
