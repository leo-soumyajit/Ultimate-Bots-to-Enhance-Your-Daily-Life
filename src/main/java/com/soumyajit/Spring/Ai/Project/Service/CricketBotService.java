package com.soumyajit.Spring.Ai.Project.Service;

import com.soumyajit.Spring.Ai.Project.DTOS.TextSummarizeBotDTOS;
import com.soumyajit.Spring.Ai.Project.DTOS.cricketBotDTOS;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import java.io.IOException;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CricketBotService {

    private final ChatModel chatModel;
    private final RestTemplate restTemplate;

    public cricketBotDTOS generateResponse(String inputContent) {

        String promptString = null;
        try {
            promptString = this.loadPrompt("/prompts/cricketBotPrompt.txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String prompt = this.putValuesInPromptTemplate(promptString,Map.of(
                "inputContent",inputContent
        ));

        ChatResponse cricketResponse = chatModel.call(
                new Prompt(prompt)
        );
        //get Content as String
        String responseString = cricketResponse.getResult().getOutput().getContent();
        cricketBotDTOS cricketBotDTOS = new cricketBotDTOS();
        cricketBotDTOS.setContent(responseString);
        return cricketBotDTOS;
    }


    // Method to fetch live cricket updates
    public String getLiveUpdates() {
        String apiUrl = "https://api.cricapi.com/v1/currentMatches?apikey=12161c62-73eb-47ae-86a5-1da60d28c334&offset=0";
        try {
            String response = restTemplate.getForObject(apiUrl, String.class);

            // Parse the JSON response
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode root = objectMapper.readTree(response);
            StringBuilder formattedResponse = new StringBuilder();

            for (JsonNode match : root.path("data")) {
                formattedResponse.append("Match: ").append(match.path("name").asText()).append("\n");
                formattedResponse.append("Type: ").append(match.path("matchType").asText()).append("\n");
                formattedResponse.append("Status: ").append(match.path("status").asText()).append("\n");
                formattedResponse.append("Venue: ").append(match.path("venue").asText()).append("\n");
                formattedResponse.append("Teams: ").append(match.path("teams").get(0).asText()).append(" vs ").append(match.path("teams").get(1).asText()).append("\n");

                JsonNode scoreNode = match.path("score");
                if (scoreNode.isArray()) {
                    for (JsonNode score : scoreNode) {
                        formattedResponse.append("Score: ").append(score.path("inning").asText()).append(" - ").append(score.path("r").asText()).append("/")
                                .append(score.path("w").asText()).append(" in ").append(score.path("o").asText()).append(" overs\n");
                    }
                }

                formattedResponse.append("\n");
            }

            return formattedResponse.toString();
        } catch (RestClientException | IOException e) {
            // Handle the exception gracefully
            return "Failed to fetch live updates. Please try again later.";
        }
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
