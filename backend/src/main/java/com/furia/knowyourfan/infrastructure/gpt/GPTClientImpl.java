package com.furia.knowyourfan.infrastructure.gpt;

import com.furia.knowyourfan.domain.gateway.GPTClient;
import com.furia.knowyourfan.domain.model.FanProfile;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.service.tool.HallucinatedToolNameStrategy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class GPTClientImpl implements GPTClient {

    private final GptAssistant assistant;
    private final FuriaTools furiaTools;

    public GPTClientImpl(@Value("${openai.api.key}") String apiKey, FuriaTools furiaTools) {
        this.furiaTools = furiaTools;
        this.assistant = AiServices.builder(GptAssistant.class)
                .chatModel(OpenAiChatModel.builder()
                        .apiKey(apiKey)
                        .modelName("gpt-4o-mini-2024-07-18")
                        .temperature(1.0)
                        .timeout(Duration.ofSeconds(60))
                        .logRequests(true)
                        .logResponses(true)
                        .build())
                .tools(furiaTools)
                .build();
    }

    @Override
    public String generateFanProfileAnalysis(FanProfile fanProfile) {
        String prompt = """
Você é um assistente digital especializado em analisar e engajar fãs do time de esports FURIA.

Ferramentas disponíveis que você pode usar:
- getUpcomingEvents(): retorna uma lista de eventos futuros envolvendo a FURIA Esports.
- getStreamerLinkByName(nome): retorna o link da transmissão ao vivo de um jogador da FURIA, caso esteja disponível.
Use apenas essas ferramentas para obter dados externos.

Abaixo estão as informações do perfil do fã. Com base nesses dados, gere um resumo personalizado que inclua:
1. Observações e percepções sobre as preferências de jogo do fã.
2. Ações sugeridas para aumentar o engajamento com os conteúdos e a comunidade da FURIA.
3. Caso existam, liste **eventos futuros da FURIA** que o fã possa gostar.
4. Caso aplicável, sugira **streamers que estejam ao vivo** no gênero de jogo favorito do fã.
5. Se o fã tiver um jogador favorito, tente encontrar e sugerir o link da transmissão ao vivo desse jogador usando as ferramentas disponíveis.

Perfil do fã:
- Apelido: %s
- Plataforma preferida: %s
- Gênero de jogo favorito: %s
- Estilo de jogo: %s
- Jogador favorito: %s
- Time favorito: %s
- Horas de jogo por semana: %s
""".formatted(
                fanProfile.getNickname(),
                fanProfile.getPreferredPlatform(),
                fanProfile.getFavoriteGenre(),
                fanProfile.getPlayStyle(),
                fanProfile.getFavoritePlayer(),
                fanProfile.getFavoriteTeam(),
                fanProfile.getGameHoursPerWeek()
        );

        return assistant.chat(prompt);
    }

    @Override
    public String generateProfile(String prompt) {
        return assistant.chat(prompt);
    }

    interface GptAssistant {
        String chat(String userMessage);
    }
}