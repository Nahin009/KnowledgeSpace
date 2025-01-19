package org.example.quiz_service.controller;


import org.example.quiz_service.dto.AiLearn.TopicRequest;
import org.example.quiz_service.service.AiLearnService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/aiLearn")
public class aiLearnController {

    private final AiLearnService aiLearnService;

    public aiLearnController(AiLearnService aiLearnService) {
        this.aiLearnService = aiLearnService;
    }

    @PostMapping("/generate-content")
    public ResponseEntity<String> generateContent(@RequestBody TopicRequest topicRequest) {
        System.out.println("Received request to generate content for topic: ");
        return aiLearnService.generateContent(topicRequest);
    }
}




