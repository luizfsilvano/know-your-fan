package com.furia.knowyourfan.domain.model;

import com.furia.knowyourfan.domain.model.enums.GameGenre;
import com.furia.knowyourfan.domain.model.enums.Platform;
import com.furia.knowyourfan.domain.model.enums.PlayStyle;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@NoArgsConstructor
public class FanProfile {

    private UUID id;
    private String nickname;
    private Platform preferredPlatform;
    private GameGenre favoriteGenre;
    private PlayStyle playStyle;
    private String favoritePlayer;
    private String favoriteTeam;
    private String gameHoursPerWeek;
    @Setter
    private String profileSummary;
    private LocalDateTime createdAt;

    public static FanProfile create(
            String nickname,
            Platform preferredPlatform,
            GameGenre favoriteGenre,
            PlayStyle playStyle,
            String favoritePlayer,
            String favoriteTeam,
            String gameHoursPerWeek,
            String profileSummary) {

        FanProfile profile = new FanProfile();
        profile.id = UUID.randomUUID();
        profile.nickname = nickname;
        profile.preferredPlatform = preferredPlatform;
        profile.favoriteGenre = favoriteGenre;
        profile.playStyle = playStyle;
        profile.favoritePlayer = favoritePlayer;
        profile.favoriteTeam = favoriteTeam;
        profile.gameHoursPerWeek = gameHoursPerWeek;
        profile.profileSummary = profileSummary;
        profile.createdAt = LocalDateTime.now();

        return profile;
    }

}