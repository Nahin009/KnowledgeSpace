package org.example.intellilearn_backend.dto.AiQuiz;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class questionsDto {
    private UUID id;
    private String question;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
}
