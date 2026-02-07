package com.example.subscriptions.domain.port.in;

import com.example.subscriptions.domain.model.User;

public interface CreateUserUseCase {

    User createUser(CreateUserCommand command);

    record CreateUserCommand(
            String name,
            String surname,
            String address,
            String login,
            String password
    ) {}
}
