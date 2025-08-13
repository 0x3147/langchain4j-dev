package com.mofish;

import com.mofish.aiservice.Assistant;
import dev.langchain4j.community.model.dashscope.QwenChatModel;
import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.response.ChatResponse;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;

@SpringBootTest
@ActiveProfiles("local")
public class ChatMemoryTest {

    @Resource
    private Assistant assistant;

    @Resource
    private QwenChatModel qwenChatModel;

    @Test
    public void chatMemory() {
        String answer = assistant.chat("我是老八");
        System.out.println(answer);
        String answer2 = assistant.chat("我是谁？");
        System.out.println(answer2);
    }

    @Test
    public void chatMemory2() {
        UserMessage userMessage1 = UserMessage.userMessage("我是老八");
        ChatResponse chat1 = qwenChatModel.chat(userMessage1);
        AiMessage aiMessage1 = chat1.aiMessage();
        System.out.println(aiMessage1.text());

        UserMessage userMessage2 = UserMessage.userMessage("你知道我是谁吗？");
        ChatResponse chat2 = qwenChatModel.chat(Arrays.asList(userMessage1, aiMessage1, userMessage2));
        AiMessage aiMessage2 = chat2.aiMessage();
        System.out.println(aiMessage2.text());
    }
}
