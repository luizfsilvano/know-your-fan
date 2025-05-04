package com.furia.knowyourfan.application.service;

import com.furia.knowyourfan.domain.gateway.GPTClient;
import com.furia.knowyourfan.domain.model.FanProfile;
import com.furia.knowyourfan.web.dto.FanProfileRequest;
import com.furia.knowyourfan.web.dto.FanProfileResponse;
import org.springframework.stereotype.Service;

@Service
public class FanProfileService {

    private final GPTClient gptClient;

    public FanProfileService(GPTClient gptClient) {
        this.gptClient = gptClient;
    }

    public FanProfile createProfile(FanProfileRequest request) {
        return FanProfile.create(
                request.nickname(),
                request.preferredPlatform(),
                request.favoriteGenre(),
                request.playStyle(),
                request.favoritePlayer(),
                request.favoriteTeam(),
                request.gameHoursPerWeek(),
                ""
        );
    }

    public FanProfileResponse generateProfileResponse(FanProfile fanProfile) {
        String profileSummary = gptClient.generateFanProfileAnalysis(fanProfile);

        fanProfile.setProfileSummary(profileSummary);

        return new FanProfileResponse(
                fanProfile.getId(),
                fanProfile.getNickname(),
                fanProfile.getPreferredPlatform(),
                fanProfile.getFavoriteGenre(),
                fanProfile.getPlayStyle(),
                fanProfile.getFavoritePlayer(),
                fanProfile.getFavoriteTeam(),
                fanProfile.getGameHoursPerWeek(),
                profileSummary,
                fanProfile.getCreatedAt()
        );
    }
}