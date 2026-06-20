package com.koushik.audiogpt.prompt;

public enum PromptType {

    RECOMMEND_SPEAKER("recommend-speaker.st"),
    COMPARE_SPEAKERS("compare-speakers.st"),
    RECOMMEND_SPEAKER_2("recommend-speaker-2.st");

    private final String fileName;

    PromptType(String fileName) {
        this.fileName = fileName;
    }

    public String fileName() {
        return fileName;
    }
}