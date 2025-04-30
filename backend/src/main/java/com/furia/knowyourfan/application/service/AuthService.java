package com.furia.knowyourfan.application.service;

import com.furia.knowyourfan.domain.model.User;
import com.furia.knowyourfan.domain.repository.UserRepository;
import com.furia.knowyourfan.infrastructure.security.JwtService;
import com.furia.knowyourfan.web.dto.AuthResponse;
import com.furia.knowyourfan.web.dto.SignupRequest;
import org.springframework.cglib.core.Local;
import org.springframework.security.crypto.password.PasswordEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthResponse registerUser(SignupRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already registered");
        }
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Username already registered");
        }

        UUID id = UUID.randomUUID();
        String encodedPassword = passwordEncoder.encode(request.getPassword());
        LocalDateTime now = LocalDateTime.now();

        User user = User.builder()
                .id(id)
                .email(request.getEmail())
                .username(request.getUsername())
                .password(encodedPassword)
                .createdAt(now)
                .updatedAt(now)
                .build();

        userRepository.save(user);

        String token = jwtService.generateToken(user);

        return AuthResponse.builder()
                .token(token)
                .build();

    }
}
