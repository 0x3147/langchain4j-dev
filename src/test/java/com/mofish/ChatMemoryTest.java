package com.mofish;

import com.mofish.aiservice.Assistant;
import com.mofish.chatmemory.SeparateAssistant;
import com.mofish.chatmemory.SeparateMysqlAssistant;
import dev.langchain4j.community.model.dashscope.QwenChatModel;
import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.response.ChatResponse;
import dev.langchain4j.service.AiServices;
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

    @Resource
    private SeparateAssistant separateAssistant;

    @Resource
    private SeparateMysqlAssistant separateMysqlAssistant;

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

    @Test
    public void chatMemory3() {
        MessageWindowChatMemory messageWindowChatMemory = MessageWindowChatMemory.withMaxMessages(10);

        Assistant assistant = AiServices
                .builder(Assistant.class)
                .chatModel(qwenChatModel)
                .chatMemory(messageWindowChatMemory)
                .build();

        String answer1 = assistant.chat("我是老八");
        System.out.println(answer1);

        String answer2 = assistant.chat("你知道我是谁吗？");
        System.out.println(answer2);
    }

    @Test
    public void chatMemory4() {
        String answer1 = assistant.chat("我是老八");
        System.out.println(answer1);

        String answer2 = assistant.chat("你知道我是谁吗？");
        System.out.println(answer2);
    }

    @Test
    public void chatMemory5() {
        String answer1 = separateAssistant.chat(1, "我是老八");
        System.out.println(answer1);

        String answer2 = separateAssistant.chat(1, "你知道我是谁吗？");
        System.out.println(answer2);

        String answer3 = separateAssistant.chat(2, "你知道我是谁吗？");
        System.out.println(answer3);
    }

    @Test
    public void chatMemory6() {
        String answer1 = separateMysqlAssistant.chat(1, "我是老八");
        System.out.println(answer1);

        String answer2 = separateMysqlAssistant.chat(1, "你知道我是谁吗？");
        System.out.println(answer2);

        String answer3 = separateMysqlAssistant.chat(2, "你知道我是谁吗？");
        System.out.println(answer3);
    }

    @Test
    public void testSysMessages() {
        String answer1 = separateMysqlAssistant.chat(4, "今天是几号？");
        System.out.println(answer1);
    }
}
