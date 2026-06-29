package com.koushik.audiogpt.prompt;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.stringtemplate.v4.ST;

import java.nio.charset.StandardCharsets;

@Service
@Slf4j
public class PromptProviderImpl implements PromptProvider {

    @Override
    @SneakyThrows
    public String getPrompt(PromptType promptType, PromptModel promptModel) {

        var resource = new ClassPathResource("prompts/" + promptType.fileName());
        String templateText = resource.getContentAsString(StandardCharsets.UTF_8);
        PromptTemplate promptTemplate = new PromptTemplate(templateText);

        // using StringTemplate v4
        ST st = new ST(templateText);

        promptModel.variables().forEach(st::add);

        log.debug("\nPrompt template loaded for {}: {}", promptType, templateText);

        return st.render();
    }

}
