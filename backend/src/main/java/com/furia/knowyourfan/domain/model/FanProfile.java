package com.furia.knowyourfan.domain.model;

import com.furia.knowyourfan.domain.model.enums.GameGenre;
import com.furia.knowyourfan.domain.model.enums.Platform;
import com.furia.knowyourfan.domain.model.enums.PlayStyle;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class FanProfile {

    private final UUID id;
    private final String nickname;
    private final Platform preferredPlatform;
    private final GameGenre favoriteGenre;
    private final PlayStyle playStyle;
    private final String favoritePlayer;
    private final String favoriteTeam;
    private final String gameHoursPerWeek;
    private final String profileSummary;
    private final LocalDateTime createdAt;

    public static FanProfile create(
            String nickname,
            Platform preferredPlatform,
            GameGenre favoriteGenre,
            PlayStyle playStyle,
            String favoritePlayer,
            String favoriteTeam,
            String gameHoursPerWeek,
            String profileSummary
    ) {
        return new FanProfile(
                UUID.randomUUID(),
                nickname,
                preferredPlatform,
                favoriteGenre,
                playStyle,
                favoritePlayer,
                favoriteTeam,
                gameHoursPerWeek,
                profileSummary,
                LocalDateTime.now()
        );
    }
}