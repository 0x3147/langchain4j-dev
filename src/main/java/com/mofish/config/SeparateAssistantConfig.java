package com.mofish.config;

import com.mofish.chatmemory.PersistentChatMemoryStore;
import dev.langchain4j.memory.chat.ChatMemoryProvider;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.store.memory.chat.InMemoryChatMemoryStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SeparateAssistantConfig {

    @Bean
    ChatMemoryProvider chatMemoryProvider() {
        return (memoryId) -> MessageWindowChatMemory
                .builder()
                .id(memoryId)
                .chatMemoryStore(new InMemoryChatMemoryStore())
                .maxMessages(10)
                .build();
    }

    @Bean
    ChatMemoryProvider chatMemoryMysqlProvider() {
        return (memoryId) -> MessageWindowChatMemory
                .builder()
                .id(memoryId)
                .chatMemoryStore(new PersistentChatMemoryStore())
                .maxMessages(10)
                .build();
    }
}
