package org.example.intellilearn_backend.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GeminiConfig {

    @Value("${GEMINI_API_KEY}")
    private String apiKey;

    @Bean
    public String googleApiKey() {
        return apiKey;
    }
}

