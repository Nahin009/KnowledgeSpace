package org.example.quiz_service.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ImageConfig {

    @Value("${STABILITY_AI_API_KEY}")
    private String apiKey;

    @Value("https://api.stability.ai")
    private String baseUrl;

    @Bean
    public String ImageAPiKey() {
        return apiKey;
    }

    @Bean
    public String ImageBaseUrl() {
        return baseUrl;
    }
}
