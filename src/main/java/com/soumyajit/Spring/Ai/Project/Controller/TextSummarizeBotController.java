package com.soumyajit.Spring.Ai.Project.Controller;
import com.soumyajit.Spring.Ai.Project.DTOS.TextSummarizeBotDTOS;
import com.soumyajit.Spring.Ai.Project.Service.TextSummarizeBotService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TextSummarizeBotController {
    private final TextSummarizeBotService textSummarizeBotService;

    @GetMapping("TextSummarize")
    public ResponseEntity<TextSummarizeBotDTOS> generateResponse(@RequestParam String inputContent)  {
        return ResponseEntity.ok(textSummarizeBotService.generateResponse(inputContent));
    }

}
