package com.furia.knowyourfan.web.dto;

import com.furia.knowyourfan.domain.model.enums.GameGenre;
import com.furia.knowyourfan.domain.model.enums.Platform;
import com.furia.knowyourfan.domain.model.enums.PlayStyle;

import java.time.LocalDateTime;
import java.util.UUID;

public record FanProfileResponse(
        UUID id,
        String nickname,
        Platform preferredPlatform,
        GameGenre favoriteGenre,
        PlayStyle playStyle,
        String favoritePlayer,
        String favoriteTeam,
        String gameHoursPerWeek,
        String profileSummary,
        LocalDateTime createdAt
) {
}
