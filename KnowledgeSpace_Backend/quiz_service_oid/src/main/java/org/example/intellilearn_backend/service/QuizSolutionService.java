package org.example.intellilearn_backend.service;

import org.example.intellilearn_backend.dto.AiQuiz.answerDto;
import org.example.intellilearn_backend.dto.AiQuiz.answerResponseDto;
import org.springframework.stereotype.Service;
import org.example.intellilearn_backend.repository.questionRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuizSolutionService {
    private final questionRepository questionRepository;

    public QuizSolutionService(org.example.intellilearn_backend.repository.questionRepository questionRepository) {
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
