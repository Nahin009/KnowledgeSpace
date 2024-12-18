package org.example.intellilearn_backend.controller;

import org.example.intellilearn_backend.dto.AiLearn.TopicRequest;
import org.example.intellilearn_backend.service.AiLearnService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/aiLearn")
public class aiLearnController {

    private final AiLearnService aiLearnService;

    public aiLearnController(AiLearnService aiLearnService) {
        this.aiLearnService = aiLearnService;
    }

    @PostMapping("/generateContent")
    public ResponseEntity<String> generateContent(@RequestBody TopicRequest topicRequest) {
        return aiLearnService.generateContent(topicRequest);
    }
}




