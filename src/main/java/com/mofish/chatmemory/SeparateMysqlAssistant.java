package com.mofish.chatmemory;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.spring.AiService;

import static dev.langchain4j.service.spring.AiServiceWiringMode.EXPLICIT;

@AiService(
        wiringMode = EXPLICIT,
        chatModel = "qwenChatModel",
        chatMemoryProvider = "chatMemoryMysqlProvider"
)
public interface SeparateMysqlAssistant {

    String chat(@MemoryId int memoryId, @UserMessage String userMessage);
}
