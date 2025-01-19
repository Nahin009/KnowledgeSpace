package org.example.intellilearn_backend.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.intellilearn_backend.dto.AiLearn.TopicRequest;
import org.example.intellilearn_backend.dto.AiQuiz.AiQuizApiResponse;
import org.example.intellilearn_backend.util.TextParser;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class AiLearnService {
    private final RestTemplate restTemplate;
    private final String apiKey;
    private final ImageService stabilityApiService;



    public AiLearnService(@Qualifier("restTemplate") RestTemplate restTemplate, @Qualifier("googleApiKey") String apiKey, ImageService stabilityApiService) {
        this.restTemplate = restTemplate;
        this.apiKey = apiKey;
        this.stabilityApiService = stabilityApiService;
    }

    public ResponseEntity<String> generateContent(TopicRequest topicRequest) {
        try {
            // Step 1: Generate text content using OpenAI API
            String textContent = generateTextContent(topicRequest.getTopic(), topicRequest.getDetails());

            // Step 2: Extract placeholders
            List<TextParser.Placeholder> placeholders = TextParser.extractPlaceholders(textContent);

            // Step 3: Process placeholders
            for (TextParser.Placeholder placeholder : placeholders) {
                textContent = switch (placeholder.getType()) {
                    case FLOWCHART -> {
                        String encodedMermaidCode = placeholder.getDescription()
                                .replace("```mermaid", "")
                                .replace("```", "");
//                                .replace("(", "&#40;")
//                                .replace(")", "&#41;")
//                                .replace("[", "&#91;")
//                                .replace("]", "&#93;");
                        yield textContent.replace(placeholder.getTag(), "<pre class=\"mermaid\">" + encodedMermaidCode + "</pre>");
                    }
                    case IMAGE -> {
                        String imageUrl = generateImage(placeholder.getDescription());
                        yield textContent.replace(placeholder.getTag(), "<img src=\"" + imageUrl + "\" alt=\"" + placeholder.getDescription() + "\" />");
//                        yield textContent;
                    }

                };
            }

            // Return the final content
            return ResponseEntity.ok().contentType(MediaType.TEXT_PLAIN).body(textContent);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    private String generateTextContent(String topic, String details) {
        String url = "https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash:generateContent?key=" + apiKey;

        String requestJson = getPromptRequest(topic, details);

        String response = restTemplate.postForObject(url, requestJson, String.class);

//        System.out.println(response);

        try {
            return extractFromResponse(response);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private static String getPromptRequest(String topic, String details) {
        String fullText = "Generate a lecture about topic:" + topic + ". Details:" + details + ". Include images and flowcharts only if they are necessary for understanding. Use the format: \n\n[Lecture Text]\n\n# Image Placeholder: [Description of the image] (if needed)\n\n[Lecture Text]\n\n# Flowchart Placeholder: [Mermaid code for flowchart] (if needed).\n\n[Lecture Text]. And also give response in html format, not in markdown format.";

        String requestJson = "{ \"contents\": [{ \"parts\":[{ \"text\": \"" + fullText + "\"}]}]}";
        return requestJson;
    }

    private String generateImage(String description) {
        try {
            return stabilityApiService.generateImage(description);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }
    //
    private String extractFromResponse(String response) throws JsonProcessingException {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            AiQuizApiResponse aiQuizApiResponse = objectMapper.readValue(response, AiQuizApiResponse.class);
            String extractedResponse = aiQuizApiResponse.getCandidates().getFirst().getContent().getParts().getFirst().getText();
            System.out.println(extractedResponse);
            return extractedResponse;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "Error extracting response";
        }
    }
}
