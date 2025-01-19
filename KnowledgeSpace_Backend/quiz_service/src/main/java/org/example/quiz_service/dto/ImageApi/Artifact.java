package org.example.quiz_service.dto.ImageApi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Artifact {
    // Getter and Setter
    private String base64;

}
