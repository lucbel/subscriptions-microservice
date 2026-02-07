package com.example.subscriptions.domain.port.out;

import com.example.subscriptions.domain.model.SessionToken;

import java.util.Optional;

public interface SessionTokenRepositoryPort {

    SessionToken save(SessionToken sessionToken);

    Optional<SessionToken> findByToken(String token);

    void deleteByToken(String token);

    void deleteByUserId(Long userId);

    void deleteExpiredTokens();
}
