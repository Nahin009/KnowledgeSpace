package org.example.quiz_service.service;

import org.example.quiz_service.dto.AiQuiz.answerDto;
import org.example.quiz_service.dto.AiQuiz.answerResponseDto;
import org.example.quiz_service.repository.questionRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuizSolutionService {
    private final questionRepository questionRepository;

    public QuizSolutionService(questionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public List<answerResponseDto> getSolutions(List<answerDto> answers) {
        List<answerResponseDto> solutions = new ArrayList<>();
        for (answerDto answer : answers) {
            answerResponseDto solution = new answerResponseDto();
            solution.setQuestionId(answer.getQuestionId());
            solution.setAnswerOptionNo(answer.getAnswerOptionNo());
            if (questionRepository.findById(answer.getQuestionId()).isPresent()) {
                solution.setCorrectOptionNo(questionRepository.findById(answer.getQuestionId()).get().getCorrectOptionNo());
                solution.setSolutionDescription(questionRepository.findById(answer.getQuestionId()).get().getSolutionDescription());
            }
            solutions.add(solution);
        }
        return solutions;
    }
}
