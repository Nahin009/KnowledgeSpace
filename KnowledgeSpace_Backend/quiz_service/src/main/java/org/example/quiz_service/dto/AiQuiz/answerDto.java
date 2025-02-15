package org.example.quiz_service.dto.AiQuiz;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class answerDto {
    private UUID questionId;
    private int answerOptionNo;
}
