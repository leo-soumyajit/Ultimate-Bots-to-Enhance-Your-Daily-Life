package com.soumyajit.Spring.Ai.Project.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.soumyajit.Spring.Ai.Project.DTOS.CricketBotDTOS;
import com.soumyajit.Spring.Ai.Project.Service.CricketBotService;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CricketBotController {
    private final CricketBotService cricketBotService;

    @GetMapping("cricket")
    public CricketBotDTOS generateResponse(@RequestParam String inputContent)  {
        try{
            return cricketBotService.generateResponse(inputContent);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
