package com.av.first.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;



@Service
public class GeminiService {

    private final RestTemplate restTemplate = new RestTemplate();

    public String generateContent() {

        String url = "https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash-latest:generateContent?key=AIzaSyB0RnMDJqovgChfWBS8EVYb2KqMYQikiPI";

        String requestJson = "{\"contents\":[{\"parts\":[{\"text\":\"o que são estomias\"}]}]}";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(requestJson, headers);

        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, entity, String.class);

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            try {

                ObjectMapper mapper = new ObjectMapper();

                JsonNode root = mapper.readTree(responseEntity.getBody());

                return root.path("candidates").get(0).path("content").path("parts").get(0).path("text").asText();
            } catch (JsonProcessingException e) {

                return "Erro ao processar a resposta: " + e.getMessage();
            }
        } else {

            return "Falha ao obter dados. Código de status: " + responseEntity.getStatusCode();
        }
    }
}