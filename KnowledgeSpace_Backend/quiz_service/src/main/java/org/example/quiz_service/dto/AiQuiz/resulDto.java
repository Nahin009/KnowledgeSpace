package org.example.quiz_service.dto.AiQuiz;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
class solutions {
    private UUID id;
    private int correctOption;
    private int answeredOption;
    private String ansDescription;
}

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class resulDto {
    private String score;
    List<solutions> solutions;
}
