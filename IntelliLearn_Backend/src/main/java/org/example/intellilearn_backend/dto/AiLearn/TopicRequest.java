package org.example.intellilearn_backend.dto.AiLearn;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class TopicRequest {
    private String topic;
    private String details;
}
