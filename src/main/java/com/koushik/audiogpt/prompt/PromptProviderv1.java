package com.koushik.audiogpt.prompt;

public interface PromptProviderv1 {

    String getPrompt(PromptType promptType,
                     Object promptModel);
}
