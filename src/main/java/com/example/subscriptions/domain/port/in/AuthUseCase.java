package com.example.subscriptions.domain.port.in;

import com.example.subscriptions.domain.model.SessionToken;

public interface AuthUseCase {

    SessionToken login(LoginCommand command);

    void logout(String token);

    record LoginCommand(
            String login,
            String password
    ) {}
}
