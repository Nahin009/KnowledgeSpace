package org.example.intellilearn_backend.dto.AiQuiz;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class topicDto {
    private String title;
    private String description;
}