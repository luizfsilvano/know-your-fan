package com.furia.knowyourfan.infrastructure.gpt;

import com.furia.knowyourfan.application.service.FuriaInfoService;
import com.furia.knowyourfan.domain.gateway.GPTClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Component
@RequiredArgsConstructor
public class GPTClientImpl implements GPTClient {

    @Value("${openai.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();
    private final FuriaInfoService furiaInfoService;

    @Override
    public String generateProfile(String prompt) {
        String url = "https://api.openai.com/v1/chat/completions";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);

        Map<String, Object> functionDefinition = Map.of(
                "name", "getStreamerLinkByName",
                "description", "Retorna o link da live de um streamer da FURIA",
                "parameters", Map.of(
                        "type", "object",
                        "properties", Map.of(
                                "name", Map.of(
                                        "type", "string",
                                        "description", "Nome do streamer da FURIA"
                                )
                        ),
                        "required", List.of("name")
                )
        );

        List<Map<String, Object>> initialMessages = List.of(
                Map.of("role", "system", "content", "Você é uma IA da FURIA que entende profundamente sobre fãs e jogos."),
                Map.of("role", "user", "content", prompt)
        );

        Map<String, Object> requestBody = Map.of(
                "model", "gpt-4o-mini-2024-07-18",
                "temperature", 0.8,
                "functions", List.of(functionDefinition),
                "messages", initialMessages
        );

        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, headers);

        try {
            ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, Map.class);
            Map<String, Object> responseBody = response.getBody();

            if (responseBody == null) return "[Erro] Resposta vazia da OpenAI.";

            List<Map<String, Object>> choices = (List<Map<String, Object>>) responseBody.get("choices");
            if (choices == null || choices.isEmpty()) return "[Erro] Nenhuma escolha retornada.";

            Map<String, Object> firstChoice = choices.get(0);

            if (firstChoice.containsKey("tool_calls")) {
                List<Map<String, Object>> toolCalls = (List<Map<String, Object>>) firstChoice.get("tool_calls");
                for (Map<String, Object> toolCall : toolCalls) {
                    Map<String, Object> functionMap = (Map<String, Object>) toolCall.get("function");
                    String functionName = (String) functionMap.get("name");
                    String functionArgsJson = (String) functionMap.get("arguments");

                    if ("getStreamerLinkByName".equals(functionName)) {
                        String name = extractNameFromArguments(functionArgsJson);
                        String toolResponse = furiaInfoService.getStreamerLinkByName(name);

                        Map<String, Object> followUpBody = Map.of(
                                "model", "gpt-4o-mini-2024-07-18",
                                "temperature", 0.8,
                                "messages", List.of(
                                        initialMessages.get(0), // system
                                        initialMessages.get(1), // user
                                        Map.of(
                                                "role", "tool",
                                                "name", "getStreamerLinkByName",
                                                "content", toolResponse
                                        )
                                )
                        );

                        HttpEntity<Map<String, Object>> followUpRequest = new HttpEntity<>(followUpBody, headers);
                        ResponseEntity<Map> finalResponse = restTemplate.exchange(url, HttpMethod.POST, followUpRequest, Map.class);

                        Map<String, Object> finalBody = finalResponse.getBody();
                        if (finalBody != null) {
                            List<Map<String, Object>> finalChoices = (List<Map<String, Object>>) finalBody.get("choices");
                            if (finalChoices != null && !finalChoices.isEmpty()) {
                                return (String) ((Map<String, Object>) finalChoices.get(0).get("message")).get("content");
                            }
                        }
                        return "[Erro] GPT não retornou resposta final.";
                    }
                }
            }

            Map<String, Object> message = (Map<String, Object>) firstChoice.get("message");
            return (String) message.get("content");

        } catch (Exception e) {
            e.printStackTrace();
            return "[Erro] Falha ao gerar perfil via OpenAI.";
        }
    }

    private String extractNameFromArguments(String json) {
        try {
            json = json.trim().replaceAll("[{}\"]", "");
            for (String part : json.split(",")) {
                String[] keyValue = part.split(":");
                if (keyValue[0].trim().equals("name")) {
                    return keyValue[1].trim();
                }
            }
        } catch (Exception ignored) {}
        return "desconhecido";
    }
}
