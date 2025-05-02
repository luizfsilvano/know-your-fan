package com.furia.knowyourfan.application.service;

import com.furia.knowyourfan.domain.gateway.GPTClient;
import com.furia.knowyourfan.domain.model.FanProfile;
import com.furia.knowyourfan.web.dto.FanProfileRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FanProfileService {

    private final GPTClient gptClient;

    public FanProfile createProfile(FanProfileRequest request) {
        String prompt = buildPromptFromRequest(request);
        String profileSummary = gptClient.generateProfile(prompt);

        return FanProfile.create(
                request.nickname(),
                request.preferredPlatform(),
                request.favoriteGenre(),
                request.playStyle(),
                request.favoritePlayer(),
                request.favoriteTeam(),
                request.gameHoursPerWeek(),
                profileSummary
        );
    }

    private String buildPromptFromRequest(FanProfileRequest request) {
        return """
                Você é uma IA da FURIA pronta para dar um perfil do usuário e dar sugestões.
                Baseado nas seguintes preferencias, gere um curto e personalizado perfil do fan da FURIA e de sugestões de conteúdo para consumir:
                
                Nickname: %s
                Platform: %s
                Genre: %s
                Play Style: %s
                Favorite Player: %s
                Favorite Team: %s
                Weekly Hours: %s
                
                Responda com um resumo de 2 a 3 frases focando na personalidade e no estilo do fã
                """.formatted(
                request.nickname(),
                request.preferredPlatform(),
                request.favoriteGenre(),
                request.playStyle(),
                request.favoritePlayer() != null ? request.favoritePlayer() : "Not provided",
                request.favoriteTeam() != null ? request.favoriteTeam() : "Not provided",
                request.gameHoursPerWeek() != null ? request.gameHoursPerWeek() : "Not provided"
        );
    }
}
