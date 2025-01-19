package org.example.intellilearn_backend.controller;
import org.example.intellilearn_backend.dto.AiQuiz.answerDto;
import org.example.intellilearn_backend.dto.AiQuiz.answerResponseDto;
import org.example.intellilearn_backend.dto.AiQuiz.questionsDto;
import org.example.intellilearn_backend.dto.AiQuiz.topicDto;
import org.example.intellilearn_backend.service.AiQuizService;
import org.example.intellilearn_backend.service.QuizSolutionService;
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
}
