package org.example.quiz_service.service;


import lombok.Getter;
import lombok.Setter;
import org.example.quiz_service.dto.ImageApi.Artifact;
import org.example.quiz_service.dto.ImageApi.RequestPayload;
import org.example.quiz_service.dto.ImageApi.ResponsePayload;
import org.example.quiz_service.dto.ImageApi.TextPrompt;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class ImageService {


    private final String STABILITY_AI_API_KEY;
    private final String STABILITY_AI_BASE_URL;

    private final RestTemplate restTemplate;
    private static final String LOCAL_PATH = "quiz_service/src/main/resources/static/images/";
    private static final String IMAGE_SERVER_UPLOAD_URL = "http://localhost:7000/upload-image"; // Your image server upload URL

    public ImageService(@Qualifier("ImageAPiKey") String stabilityAiApiKey, @Qualifier("ImageBaseUrl") String stabilityAiBaseUrl, @Qualifier("restTemplate") RestTemplate restTemplate) {
        STABILITY_AI_API_KEY = stabilityAiApiKey;
        STABILITY_AI_BASE_URL = stabilityAiBaseUrl;
        this.restTemplate = restTemplate;
    }

    public String generateImage(String prompt) throws IOException {
        System.out.println("Generating image for prompt: " + prompt);
        String url = STABILITY_AI_BASE_URL + "/v1/generation/stable-diffusion-xl-1024-v1-0/text-to-image";
        // create a 32 character random string
        String fileName = UUID.randomUUID().toString().replace("-", "_") + ".png";
        String fullPath = LOCAL_PATH + fileName;


        TextPrompt textPrompt = new TextPrompt(prompt);
        RequestPayload requestPayload = new RequestPayload(
                Collections.singletonList(textPrompt),
                7,
                1024,
                1024,
                1,
                30
        );

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setBearerAuth(STABILITY_AI_API_KEY);

        HttpEntity<RequestPayload> request = new HttpEntity<>(requestPayload, headers);

        ResponseEntity<ResponsePayload> response = restTemplate.exchange(url, HttpMethod.POST, request, ResponsePayload.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            List<Artifact> artifacts = Objects.requireNonNull(response.getBody()).getArtifacts();
            for (Artifact artifact : artifacts) {
                byte[] imageBytes = Base64.getDecoder().decode(artifact.getBase64());
                try (FileOutputStream fos = new FileOutputStream(fullPath)) {
                    fos.write(imageBytes);
                }
                // return absolute path of the image
                return uploadImage(fullPath);
            }
        } else {
            throw new RuntimeException("Failed to generate image: " + response.getStatusCode() + " " + response.getBody());
        }
        return null;
    }

    private String uploadImage(String fullPath) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("image", new FileSystemResource(fullPath));

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        ResponseEntity<UploadResponse> response = restTemplate.postForEntity(IMAGE_SERVER_UPLOAD_URL, requestEntity, UploadResponse.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            return Objects.requireNonNull(response.getBody()).getUrl();
        } else {
            throw new RuntimeException("Failed to upload image: " + response.getStatusCode() + " " + response.getBody());
        }
    }

    @Setter
    @Getter
    public static class UploadResponse {
        private String url;
    }

//    @Value("${SPRING_AI_STABILITY_AI_API_KEY}")
//    private String apiKey;
//
//    public byte[] generateStabilityImage(String prompt) {
//
//        StabilityAiImageOptions options = new StabilityAiImageOptions();
//        options.setResponseFormat("image/png");
//
//        StabilityAiApi api = new StabilityAiApi(apiKey);
//
//        StabilityAiImageModel model = new StabilityAiImageModel(api);
//
//        String demoPrompt = "A beautiful sunset over the ocean.";
//        ImageResponse response = model.call(
//                new ImagePrompt(demoPrompt, options)
//        );
//
//        return response.getResult().getOutput().getB64Json().getBytes();
//
//
//    }
}
