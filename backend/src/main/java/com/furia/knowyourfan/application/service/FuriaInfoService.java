package com.furia.knowyourfan.application.service;

import com.furia.knowyourfan.domain.model.FuriaEvent;
import com.furia.knowyourfan.domain.repository.FuriaEventRepository;
import com.furia.knowyourfan.domain.repository.StreamerLiveStatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FuriaInfoService {

    private final StreamerLiveStatusRepository streamerLiveStatusRepository;
    private final FuriaEventRepository furiaEventRepository;

    public String getStreamerLinkByName(String name) {
        Optional<String> link = streamerLiveStatusRepository.findLinkByNameIgnoreCase(name);
        return link.orElse("Nenhum link encontrado para o streamer " + name + ".");
    }

    public String getUpcomingEvents() {
        List<FuriaEvent> events = furiaEventRepository.findUpcomingEvents(LocalDateTime.now());

        if (events.isEmpty()) {
            return "Não há eventos futuros cadastrados para a FURIA.";
        }

        StringBuilder builder = new StringBuilder("Próximos eventos da FURIA:\n");
        for (FuriaEvent event : events) {
            builder.append("- ")
                    .append(event.getTitle())
                    .append(" em ")
                    .append(event.getDateTime())
                    .append("\n");
        }

        return builder.toString();
    }
}