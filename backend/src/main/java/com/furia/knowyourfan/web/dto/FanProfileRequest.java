package com.furia.knowyourfan.web.dto;

import com.furia.knowyourfan.domain.model.enums.GameGenre;
import com.furia.knowyourfan.domain.model.enums.Platform;
import com.furia.knowyourfan.domain.model.enums.PlayStyle;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record FanProfileRequest(
        @NotBlank String nickname,
        @NotNull Platform preferredPlatform,
        @NotNull GameGenre favoriteGenre,
        @NotNull PlayStyle playStyle,
        String favoritePlayer,
        String favoriteTeam,
        String gameHoursPerWeek
) {}