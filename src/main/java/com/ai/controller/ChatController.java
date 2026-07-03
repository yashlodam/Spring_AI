package com.ai.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {

    private final ChatClient groqChatClient;
    private final ChatClient geminiChatClient;

    public ChatController(
            @Qualifier("groqChatClient") ChatClient groqChatClient,
            @Qualifier("geminiChatClient") ChatClient geminiChatClient) {

        this.groqChatClient = groqChatClient;
        this.geminiChatClient = geminiChatClient;
    }

    @GetMapping("/chat")
    public ResponseEntity<String> chat(
            @RequestParam String q,
            @RequestParam String model) {

        String response;

        if ("groq".equalsIgnoreCase(model)) {

            response = groqChatClient
                    .prompt(q)
                    .call()
                    .content();

        } else if ("gemini".equalsIgnoreCase(model)) {

            response = geminiChatClient
                    .prompt(q)
                    .call()
                    .content();

        } else {

            return ResponseEntity.badRequest()
                    .body("Invalid model. Supported models: groq, gemini");
        }

        return ResponseEntity.ok(response);
    }
}