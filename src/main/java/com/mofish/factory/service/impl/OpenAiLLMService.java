package com.mofish.factory.service.impl;

import com.mofish.factory.service.LLMService;
import dev.langchain4j.model.openai.OpenAiChatModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class OpenAiLLMService implements LLMService {

    private final OpenAiChatModel openAiChatModel;

    public OpenAiLLMService(
            @Value("${openai.api-key}") String apiKey,
            @Value("${openai.base-url}") String baseUrl,
            @Value("${openai.model-name}") String modelName) {

        // 添加调试信息
        System.out.println("OpenAI Configuration:");
        System.out.println("API Key: " + (apiKey != null ? apiKey.substring(0, Math.min(10, apiKey.length())) + "..." : "null"));
        System.out.println("Base URL: " + baseUrl);
        System.out.println("Model Name: " + modelName);

        this.openAiChatModel = OpenAiChatModel
                .builder()
                .apiKey(apiKey)
                .baseUrl(baseUrl)
                .modelName(modelName)
                .build();
    }

    @Override
    public String chat(String prompt) {
        return openAiChatModel.chat(prompt);
    }
}
