package com.example.demo.service.chat;

import com.example.demo.service.chat.ChatService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.Map;

@Service
public class ChatServiceImpl implements ChatService {
    @Value("${flask.server.url}")
    private String flaskUrl;

    @Override
    public String sendToFlask(Map<String, String> requestBody) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter(StandardCharsets.UTF_8));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Accept-Charset", "UTF-8");

        HttpEntity<Map<String, String>> entity = new HttpEntity<>(requestBody, headers);

        try {
            ResponseEntity<String> response = restTemplate.postForEntity(flaskUrl, entity, String.class);
            return response.getBody();
        } catch (Exception e) {
            throw new RuntimeException("Error communicating with Flask: " + e.getMessage());
        }
    }
}