package com.ai.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AiConfig {

    @Bean
    public ChatClient groqChatClient(
            @Qualifier("openAiChatModel") ChatModel groqChatModel) {

        return ChatClient.builder(groqChatModel).build();
    }

    @Bean
    public ChatClient geminiChatClient(
            @Qualifier("googleGenAiChatModel") ChatModel geminiChatModel) {

        return ChatClient.builder(geminiChatModel).build();
    }

}