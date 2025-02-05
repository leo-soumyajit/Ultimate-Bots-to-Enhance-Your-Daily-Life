package com.soumyajit.Spring.Ai.Project.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.soumyajit.Spring.Ai.Project.DTOS.CricketBotDTOS;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Service
@RequiredArgsConstructor
public class CricketBotService {

    private final ChatModel chatModel;

    public CricketBotDTOS generateResponse(@RequestParam String inputContent) throws JsonProcessingException {
        String promptString = "As a cricket expert, answer this question : "+inputContent
                +"If this question is not related cricket generate a joke saying this question is out of context"+
                "Give a JSON response that must contain 'content' as a key and response as a value";

        ChatResponse cricketResponse = chatModel.call(
                new Prompt(promptString)
        );
        //get Content as String
        String responseString = cricketResponse.getResult().getOutput().getContent();
//        ObjectMapper objectMapper = new ObjectMapper();
//        return objectMapper.readValue(responseString, CricketBotDTOS.class);
        CricketBotDTOS cricketBotDTOS = new CricketBotDTOS();
        cricketBotDTOS.setContent(responseString);
        return cricketBotDTOS;
    }

}
