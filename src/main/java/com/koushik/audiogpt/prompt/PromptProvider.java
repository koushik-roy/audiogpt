package com.koushik.audiogpt.prompt;

public interface PromptProvider {

    String getPrompt(PromptType promptType,
                     PromptModel promptModel);
}
