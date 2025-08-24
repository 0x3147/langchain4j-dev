package com.mofish.aiservice;

import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;
import dev.langchain4j.service.spring.AiService;

import static dev.langchain4j.service.spring.AiServiceWiringMode.EXPLICIT;

@AiService(wiringMode = EXPLICIT, chatModel = "qwenChatModel", chatMemory = "chatMemory")
public interface Assistant {

//    @UserMessage("你是我的朋友，请用文言文风格回答问题。{{it}}")
//    String chat(String userMessage);

    @UserMessage("你是我的朋友，请用动漫傲娇女主风格回答问题。{{userMessage}}")
    String chat(@V("userMessage") String userMessage);
}
