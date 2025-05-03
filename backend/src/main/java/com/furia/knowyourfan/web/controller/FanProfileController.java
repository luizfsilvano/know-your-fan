package com.furia.knowyourfan.web.controller;

import com.furia.knowyourfan.application.service.FanProfileService;
import com.furia.knowyourfan.domain.model.FanProfile;
import com.furia.knowyourfan.web.dto.FanProfileRequest;
import com.furia.knowyourfan.web.dto.FanProfileResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/fan-profile")
@RequiredArgsConstructor
public class FanProfileController {

    private final FanProfileService fanProfileService;

    @PostMapping("/generate")
    public ResponseEntity<FanProfileResponse> generateFanProfile(
            @Valid @RequestBody FanProfileRequest request) {

        FanProfile fanProfile = fanProfileService.createProfile(request);

        FanProfileResponse response = fanProfileService.generateProfileResponse(fanProfile);

        return ResponseEntity.ok(response);
    }
}