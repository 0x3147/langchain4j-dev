package com.mofish.factory;

import com.mofish.factory.service.LLMService;
import com.mofish.factory.service.impl.OpenAiLLMService;
import com.mofish.factory.service.impl.QwenLLMService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

/**
 * Spring 管理的 LLM 工厂类
 * 可以正确注入配置文件中的参数
 */
@Component
public class LLMFactory {

    @Resource
    private OpenAiLLMService openAiLLMService;

    @Resource
    private QwenLLMService qwenLLMService;

    public enum ModelType {
        OPENAI, QWEN
    }

    public LLMService createLLMService(ModelType type) {
        return switch (type) {
            case OPENAI -> openAiLLMService;
            case QWEN -> qwenLLMService;
        };
    }
}
