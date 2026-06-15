package com.koushik.audiogpt.dto;

public record ComparisonRequest(
        String speaker1,
        String speaker2
) {
}


/* Example usage:
    JSON:
    {
      "speaker1": "JBL Flip 6",
      "speaker2": "Marshall Emberton II"
    }
 */