package com.furia.knowyourfan.application.service;

import com.furia.knowyourfan.domain.repository.StreamerLiveStatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FuriaInfoService {

    private final StreamerLiveStatusRepository streamerLiveStatusRepository;

    public String getStreamerLinkByName(String name) {
        return streamerLiveStatusRepository.findByNameIgnoreCase(name)
                .map(status -> "Stream do " + name + ": " + status.getLink())
                .orElse("Nenhum link encontrado para o streamer " + name + ".");
    }
}
