package com.soumyajit.Spring.Ai.Project.Controller;


import com.soumyajit.Spring.Ai.Project.DTOS.TextSummarizeBotDTOS;
import com.soumyajit.Spring.Ai.Project.DTOS.cricketBotDTOS;
import com.soumyajit.Spring.Ai.Project.Service.CricketBotService;
import com.soumyajit.Spring.Ai.Project.Service.TextSummarizeBotService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CricketBotController {

    private final CricketBotService cricketBotService;

    @GetMapping("cricket")
    public ResponseEntity<cricketBotDTOS> generateResponse(@RequestParam String inputContent)  {
        return ResponseEntity.ok(cricketBotService.generateResponse(inputContent));
    }

    //live cricket updates
    @GetMapping("/cricket/live-updates")
    public ResponseEntity<String> getLiveUpdates() {
        String liveUpdates = cricketBotService.getLiveUpdates();
        return ResponseEntity.ok(liveUpdates);
    }

}
