package com.mofish.factory.service.impl;

import com.mofish.factory.service.LLMService;
import dev.langchain4j.community.model.dashscope.QwenChatModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class QwenLLMService implements LLMService {

    private final QwenChatModel qwenChatModel;

    public QwenLLMService(
            @Value("${dashscope.api-key}") String apiKey,
            @Value("${dashscope.model-name}") String modelName) {
        this.qwenChatModel = QwenChatModel.builder()
                .apiKey(apiKey)
                .modelName(modelName)
                .build();
    }

    @Override
    public String chat(String prompt) {
        return qwenChatModel.chat(prompt);
    }
}
