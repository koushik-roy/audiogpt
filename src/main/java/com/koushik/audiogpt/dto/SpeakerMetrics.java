package com.koushik.audiogpt.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SpeakerMetrics {
    String brand;
    String model;
    Integer bassScore;
    Integer highsScore;
    Integer midsScore;
    Integer soundQualityScore;
    Integer buildQualityScore;
    Integer valueForMoneyScore;
    Integer totalScore;
}
