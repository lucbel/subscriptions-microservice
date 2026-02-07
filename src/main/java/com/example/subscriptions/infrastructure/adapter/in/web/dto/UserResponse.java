package com.example.subscriptions.infrastructure.adapter.in.web.dto;

import com.example.subscriptions.domain.model.User;

import java.time.LocalDateTime;

public record UserResponse(
        Long id,
        String name,
        String surname,
        String address,
        String login,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public static UserResponse fromDomain(User user) {
        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getSurname(),
                user.getAddress(),
                user.getLogin(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }
}
