package org.example.quiz_service.dto.ImageApi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
public class RequestPayload {
    @JsonProperty("text_prompts")
    private List<TextPrompt> textPrompts;
    private int cfgScale;
    private int height;
    private int width;
    private int samples;
    private int steps;

}
