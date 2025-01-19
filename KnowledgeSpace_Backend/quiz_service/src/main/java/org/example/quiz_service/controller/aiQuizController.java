package org.example.quiz_service.controller;

import org.example.quiz_service.dto.AiQuiz.answerDto;
import org.example.quiz_service.dto.AiQuiz.answerResponseDto;
import org.example.quiz_service.dto.AiQuiz.questionsDto;
import org.example.quiz_service.dto.AiQuiz.topicDto;
import org.example.quiz_service.service.AiQuizService;
import org.example.quiz_service.service.QuizSolutionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/aiQuiz")
public class aiQuizController {

    private final AiQuizService aiQuizService;
    private final QuizSolutionService quizSolutionService;

    public aiQuizController(AiQuizService aiQuizService, QuizSolutionService quizSolutionService) {
        this.aiQuizService = aiQuizService;
        this.quizSolutionService = quizSolutionService;
    }

    @PostMapping("/generateQuiz")
    public List<questionsDto> generateQuiz(@RequestBody topicDto topic) {
        return aiQuizService.generateQuiz(topic);
    }

    @PostMapping("/submitAnswers")
    public List<answerResponseDto> submitAnswers(@RequestBody List<answerDto> answers) {
        return quizSolutionService.getSolutions(answers);
    }

    @GetMapping("/test")
    public String test() {
        return "Hello World";
    }
}
