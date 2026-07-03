package com.ai.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {

	private ChatClient chatClient;
	
	public ChatController(ChatClient.Builder builder) {
		this.chatClient = builder.build();
	}
	
	@GetMapping("/chat")
	public ResponseEntity<String> Chat(@RequestParam(value = "q",required = true) String q){
		
		
		var resultResponse = chatClient.prompt(q).call().content();
		
		return ResponseEntity.ok(resultResponse);
	}
	
}
