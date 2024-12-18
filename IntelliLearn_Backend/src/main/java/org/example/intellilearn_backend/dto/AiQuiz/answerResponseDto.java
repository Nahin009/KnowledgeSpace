package org.example.intellilearn_backend.dto.AiQuiz;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class answerResponseDto {
    private UUID questionId;
    private int answerOptionNo;
    private int correctOptionNo;
    private String solutionDescription;
}
