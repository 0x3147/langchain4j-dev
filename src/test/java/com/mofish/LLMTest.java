package com.mofish;

import com.mofish.factory.LLMFactory;
import com.mofish.factory.service.impl.OpenAiLLMService;
import com.mofish.factory.service.impl.QwenLLMService;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("local")  // 激活 local profile，加载 application-local.properties
public class LLMTest {

    // 移除对 LangChain4j 自动配置 Bean 的依赖，使用我们自定义的服务类

    // 注入 Spring 管理的服务类，这样可以正确加载配置文件中的参数
    @Resource
    private OpenAiLLMService openAiLLMService;

    @Resource
    private QwenLLMService qwenLLMService;

    @Test
    public void testOpenAiService() {
        String answer = openAiLLMService.chat("你好,你是谁？");
        System.out.println(answer);
    }

    @Test
    public void testQwenService() {
        String answer = qwenLLMService.chat("你好,你是谁？");
        System.out.println(answer);
    }

    // 注入 Spring 管理的工厂，可以正确加载配置文件参数
    @Resource
    private LLMFactory springLLMFactory;

    @Test
    public void testSpringFactory() {
        // 使用 Spring 管理的工厂，可以正确加载 application-local.properties 中的配置
        String answer = springLLMFactory.createLLMService(LLMFactory.ModelType.OPENAI).chat("你好,你是谁？");
        System.out.println(answer);
    }
}
