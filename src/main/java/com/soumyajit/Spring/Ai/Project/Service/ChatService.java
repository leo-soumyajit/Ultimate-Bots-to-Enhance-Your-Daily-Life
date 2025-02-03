package com.soumyajit.Spring.Ai.Project.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatService {
    private final ChatModel chatModel;
    public String getPrompt(String message) {
        return chatModel.call(message);
    }
}
