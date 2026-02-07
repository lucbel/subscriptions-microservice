package com.example.subscriptions.infrastructure.adapter.in.web.dto;

import com.example.subscriptions.domain.model.SessionToken;

import java.time.LocalDateTime;

public record LoginResponse(
        String token,
        LocalDateTime expiresAt,
        UserResponse user
) {
    public static LoginResponse fromDomain(SessionToken sessionToken) {
        return new LoginResponse(
                sessionToken.getToken(),
                sessionToken.getExpiresAt(),
                UserResponse.fromDomain(sessionToken.getUser())
        );
    }
}
