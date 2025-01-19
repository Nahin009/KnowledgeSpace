package org.example.intellilearn_backend.dto.ImageApi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponsePayload {
    private List<Artifact> artifacts;
}
