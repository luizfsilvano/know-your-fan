package com.furia.knowyourfan.domain.gateway;

import com.furia.knowyourfan.domain.model.FanProfile;

public interface GPTClient {
    String generateFanProfileAnalysis(FanProfile fanProfile);
    String generateProfile(String prompt);
}