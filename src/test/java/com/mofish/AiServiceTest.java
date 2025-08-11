package com.mofish;

import com.mofish.aiservice.Assistant;
import dev.langchain4j.community.model.dashscope.QwenChatModel;
import dev.langchain4j.service.AiServices;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("local")
public class AiServiceTest {

    @Resource
    private QwenChatModel qwenChatModel;

    @Test
    public void testChat() {
        Assistant assistant = AiServices.create(Assistant.class, qwenChatModel);
        String answer = assistant.chat("你好");
        System.out.println(answer);
    }

    @Resource
    private Assistant assistant;

    @Test
    public void testAssistant() {
        String answer = assistant.chat("你好");
        System.out.println(answer);
    }
}
