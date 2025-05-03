package com.furia.knowyourfan.infrastructure.gpt;

import com.furia.knowyourfan.domain.model.FuriaEvent;
import com.furia.knowyourfan.domain.model.StreamerLiveStatus;
import com.furia.knowyourfan.domain.repository.FuriaEventRepository;
import com.furia.knowyourfan.domain.repository.StreamerLiveStatusRepository;
import dev.langchain4j.agent.tool.Tool;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class FuriaTools {

    private final FuriaEventRepository eventRepository;
    private final StreamerLiveStatusRepository streamerRepository;

    @Tool("Busca eventos futuros da FURIA Esports")
    public List<FuriaEvent> getUpcomingEvents() {
        return eventRepository.findUpcomingEvents(LocalDateTime.now());
    }

    @Tool("Busca o link de transmissão ao vivo de um streamer da FURIA pelo nome")
    public String getStreamerLinkByName(String name) {
        return streamerRepository.findLinkByNameIgnoreCase(name)
                .orElse("No live stream link found for streamer " + name + ".");
    }
}