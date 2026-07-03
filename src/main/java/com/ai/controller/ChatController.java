package com.ai.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {

    private final ChatClient geminiChatClient;
    private final ChatClient groqChatClient;

    public ChatController(
            @Qualifier("googleGenAiChatModel") ChatModel geminiChatModel,
            @Qualifier("openAiChatModel") ChatModel openAiChatModel) {

        this.geminiChatClient = ChatClient.builder(geminiChatModel).build();
        this.groqChatClient = ChatClient.builder(openAiChatModel).build();
    }

    @GetMapping("/chat")
    public ResponseEntity<String> chat(@RequestParam(value = "q", required = true) String q) {

        String response = groqChatClient
                .prompt(q)
                .call()
                .content();

        return ResponseEntity.ok(response);
    }
}