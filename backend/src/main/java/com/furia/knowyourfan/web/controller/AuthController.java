package com.furia.knowyourfan.web.controller;

import com.furia.knowyourfan.application.service.AuthService;
import com.furia.knowyourfan.web.dto.AuthResponse;
import com.furia.knowyourfan.web.dto.SigninRequest;
import com.furia.knowyourfan.web.dto.SignupRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public AuthResponse signup(@RequestBody @Valid SignupRequest request)
    {
        // TODO: Call service to register user
        return null;
    }

    @PostMapping("/signin")
    public AuthResponse signin(@RequestBody @Valid SigninRequest request)
    {
        // TODO: Call service to authenticate user
        return null;
    }

}
