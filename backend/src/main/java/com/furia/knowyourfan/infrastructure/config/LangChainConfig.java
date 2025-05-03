package com.furia.knowyourfan.infrastructure.config;

import dev.langchain4j.model.openai.OpenAiChatModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class LangChainConfig {

    @Bean
    public OpenAiChatModel openAiChatModel(@Value("${openai.api.key}") String apiKey) {
        return OpenAiChatModel.builder()
                .apiKey(apiKey)
                .modelName("gpt-4o-mini-2024-07-18")
                .temperature(0.7)
                .timeout(Duration.ofSeconds(60))
                .build();
    }
}