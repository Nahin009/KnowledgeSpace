package org.example.quiz_service.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.quiz_service.dto.AiQuiz.AiQuizApiResponse;
import org.example.quiz_service.dto.AiQuiz.questionsDto;
import org.example.quiz_service.dto.AiQuiz.topicDto;
import org.example.quiz_service.model.AiQuiz.Question;
import org.example.quiz_service.model.AiQuiz.Quiz;
import org.example.quiz_service.repository.AiQuizRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class AiQuizService {

    private final RestTemplate restTemplate;
    private final String apiKey;
    private final AiQuizRepository quizRepository;
    private final Quiz quiz;

    public AiQuizService(@Qualifier("restTemplate") RestTemplate restTemplate, @Qualifier("googleApiKey") String apiKey, AiQuizRepository questionRepository) {
        this.restTemplate = restTemplate;
        this.apiKey = apiKey;
        this.quizRepository = questionRepository;
        quiz = new Quiz();
    }

    public List<questionsDto> generateQuiz(topicDto topic) {

        quiz.setTitle(topic.getTitle());
        quiz.setDescription(topic.getDescription());

        String url = "https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash:generateContent?key=" + apiKey;

        String fullText = getFullText(topic.getTitle(), topic.getDescription());

        String requestJson = "{ \"contents\": [{ \"parts\":[{ \"text\": \"" + fullText + "\"}]}]}";

        String response = restTemplate.postForObject(url, requestJson, String.class);
        try {
            String extractedResponse = extractFromResponse(response);
            return convertToDto(saveToDB(extractQuestions(extractedResponse)));
        } catch (JsonProcessingException e) {
            System.out.println("Error processing response or extracting questions\"");
            return null;
        }
    }

    private static String getFullText(String topic, String description) {
        String fullText;

        if(description != null) {
            fullText = "Give me 5 " + topic + " MCQs in JSON format as an array of objects: { question: string; option1: string; option2: string; option3: string; option4: string; correctOptionNo: int; solutionDescription: string; }" + "here is more description for the topic." + description + ". don't say anything extra";
        } else {
            fullText = "Give me 5 " + topic + " MCQs in JSON format as an array of objects: { question: string; option1: string; option2: string; option3: string; option4: string; correctOptionNo: int; solutionDescription: string; } dont say anything extra";
        }
        return fullText;
    }

    private String extractFromResponse(String response) throws JsonProcessingException {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            AiQuizApiResponse aiQuizApiResponse = objectMapper.readValue(response, AiQuizApiResponse.class);
            return aiQuizApiResponse.getCandidates().getFirst().getContent().getParts().getFirst().getText();
        } catch (Exception e) {
            return "Error extracting response";
        }
    }

    private List<Question> extractQuestions(String text) throws JsonProcessingException {
        String rawJsonText = text.trim();
        String jsonText = rawJsonText.replaceAll("^```json|```$", "").trim();
        System.out.println(jsonText);

        List<Question> questions;

        ObjectMapper objectMapper = new ObjectMapper();
//        StringBuilder sb = new StringBuilder();

        try {
            questions = objectMapper.readValue(jsonText, new TypeReference<List<Question>>() {});
//            for (Question question : questions) {
//                sb.append(question.toString());
//            }
//            System.out.println(sb.toString());
        } catch (IOException e) {
            return null;
        }
//        return sb.toString();
          return questions;
    }

    private Quiz saveToDB(List<Question> questions) {
        quiz.setQuestions(questions);

        for(Question question : questions) {
            question.setQuiz(quiz);
        }

        return quizRepository.save(quiz);
    }

    private List<questionsDto> convertToDto(Quiz quiz) {
        List<Question> questions = quiz.getQuestions();
        List<questionsDto> questionsDtoList = new ArrayList<>();
        for (Question question : questions) {
            questionsDto questionsDto = new questionsDto();
            questionsDto.setId(question.getId());
            questionsDto.setQuestion(question.getQuestion());
            questionsDto.setOption1(question.getOption1());
            questionsDto.setOption2(question.getOption2());
            questionsDto.setOption3(question.getOption3());
            questionsDto.setOption4(question.getOption4());
            questionsDtoList.add(questionsDto);
        }
        return questionsDtoList;
    }
}


