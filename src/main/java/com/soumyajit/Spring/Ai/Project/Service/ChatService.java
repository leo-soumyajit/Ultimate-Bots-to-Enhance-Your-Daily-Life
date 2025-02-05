package com.soumyajit.Spring.Ai.Project.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.StreamingChatModel;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class ChatService {
    private final ChatModel chatModel;
    private final StreamingChatModel streamingChatModel;

    public String getPrompt1(String message) {
        return chatModel.call(message);
    }

    public Flux<String> getPrompt2(String prompt) {
        return streamingChatModel.stream(prompt);
    }
}
