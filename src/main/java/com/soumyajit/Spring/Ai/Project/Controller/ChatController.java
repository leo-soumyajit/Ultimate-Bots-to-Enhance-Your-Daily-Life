package com.soumyajit.Spring.Ai.Project.Controller;

import com.soumyajit.Spring.Ai.Project.Service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;

    @GetMapping("/chat")
    private ResponseEntity<String> promptWithNormal(@RequestParam String message){
        return ResponseEntity.ok(chatService.getPrompt1(message));
    }
    @GetMapping("/stream")
    private ResponseEntity<Flux<String>> promptWithFluxString(@RequestParam String message){
        return ResponseEntity.ok(chatService.getPrompt2(message));
    }
}
