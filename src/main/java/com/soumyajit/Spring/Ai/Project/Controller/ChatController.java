package com.soumyajit.Spring.Ai.Project.Controller;

import com.soumyajit.Spring.Ai.Project.Service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;

    @GetMapping("/chat")
    private String prompt(@RequestParam String message){
        return chatService.getPrompt(message);
    }
}
