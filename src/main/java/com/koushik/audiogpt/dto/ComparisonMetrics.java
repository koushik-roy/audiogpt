package com.koushik.audiogpt.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ComparisonMetrics {

    Integer speaker1Total;
    Integer speaker2Total;

    String bassWinner;
    String midsWinner;
    String highsWinner;
    String soundQualityWinner;
    String buildQualityWinner;
    String valueForMoneyWinner;

    String overallWinner;

    Integer scoreDifference;

    Boolean closeComparison;
}
