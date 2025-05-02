package com.furia.knowyourfan.infrastructure.gpt;

import com.furia.knowyourfan.domain.gateway.GPTClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class GPTClientImpl implements GPTClient {

    @Value("${openai.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public String generateProfile(String prompt) {
        // TODO: Build request body for OpenAI API
        // TODO: Set headers, make POST request, handle response

        return "Você é um fã dedicado da FURIA, com estilo competitivo e paixão por FPS. Sempre pronto para torcer nos momentos decisivos!";
    }
}
