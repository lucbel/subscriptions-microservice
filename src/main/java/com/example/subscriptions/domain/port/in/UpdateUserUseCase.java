package com.example.subscriptions.domain.port.in;

import com.example.subscriptions.domain.model.User;

public interface UpdateUserUseCase {

    User updateUser(Long id, UpdateUserCommand command);

    record UpdateUserCommand(
            String name,
            String surname,
            String address,
            String login
    ) {}
}
