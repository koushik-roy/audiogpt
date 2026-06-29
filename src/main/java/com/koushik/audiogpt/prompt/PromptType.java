package com.koushik.audiogpt.prompt;

public enum PromptType {

    RECOMMEND_SPEAKER("recommend-speaker.st"),
    COMPARE_SPEAKERS("compare-speakers.st"),
    COMPARE_SPEAKERS_2("compare-speakers-2.st"),
    RECOMMEND_SPEAKER_2("recommend-speaker-2.st"),
    RECOMMEND_SPEAKER_3("recommend-speaker-3.st");

    private final String fileName;

    PromptType(String fileName) {
        this.fileName = fileName;
    }

    public String fileName() {
        return fileName;
    }
}