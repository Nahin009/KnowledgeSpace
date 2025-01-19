package org.example.intellilearn_backend.dto.ImageApi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class TextPrompt {
    private String text;

    public TextPrompt(String text) {
        this.text = text;
    }

}

