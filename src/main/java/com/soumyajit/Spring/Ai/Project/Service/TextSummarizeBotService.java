package com.soumyajit.Spring.Ai.Project.Service;
import com.soumyajit.Spring.Ai.Project.DTOS.TextSummarizeBotDTOS;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TextSummarizeBotService {

    private final ChatModel chatModel;

    public TextSummarizeBotDTOS generateResponse(@RequestParam String inputContent) {
        String promptString = null;
        try {
            promptString = this.loadPrompt("/prompts/summarizeBot.txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String prompt = this.putValuesInPromptTemplate(promptString,Map.of(
                "inputContent",inputContent
        ));

        ChatResponse response = chatModel.call(
                new Prompt(prompt)
        );
        //get Content as String
        String responseString = response.getResult().getOutput().getContent();
        TextSummarizeBotDTOS textSummarizeBotDTOS = new TextSummarizeBotDTOS();
        textSummarizeBotDTOS.setContent(responseString);
        return textSummarizeBotDTOS;
    }

    //load Prompt from class Path

    public String loadPrompt(String fileName) throws IOException {
        Path filePath = new ClassPathResource(fileName).getFile().toPath();
        return Files.readString(filePath);
    }

    //put value to the prompt

    public String putValuesInPromptTemplate(String template , Map<String , String> variables){
        for(Map.Entry<String,String> entry: variables.entrySet()){
            template = template.replace("{" + entry.getKey() + "}",entry.getValue());
        }
        return template;
    }














}
