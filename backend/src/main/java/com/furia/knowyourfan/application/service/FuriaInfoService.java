package com.furia.knowyourfan.application.service;

import com.furia.knowyourfan.domain.model.FuriaEvent;
import com.furia.knowyourfan.domain.repository.FuriaEventRepository;
import com.furia.knowyourfan.domain.repository.StreamerLiveStatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FuriaInfoService {

    private final StreamerLiveStatusRepository streamerLiveStatusRepository;
    private final FuriaEventRepository furiaEventRepository;

    public String getStreamerLinkByName(String name) {
        return streamerLiveStatusRepository.findByNameIgnoreCase(name)
                .map(status -> "Stream do " + name + ": " + status.getLink())
                .orElse("Nenhum link encontrado para o streamer " + name + ".");
    }

    public String getUpcomingEvents() {
        List<FuriaEvent> events = furiaEventRepository.findAllByDateTimeAfterOrderByDateTimeAsc(LocalDateTime.now());

        if (events.isEmpty()) {
            return "Não há eventos futuros cadastrados para a FURIA.";
        }

        StringBuilder builder = new StringBuilder("Próximos eventos da FURIA:\n");
        for (FuriaEvent event : events) {
            builder.append("- ")
                    .append(event.getTitle())
                    .append(" em ")
                    .append(event.getDateTime())
                    .append(" (")
                    .append(event.getLocation() != null ? event.getLocation() : "local não informado")
                    .append(")\n");

            if (event.getDescription() != null && !event.getDescription().isBlank()) {
                builder.append("  ").append(event.getDescription()).append("\n");
            }
        }

        return builder.toString();
    }
}
