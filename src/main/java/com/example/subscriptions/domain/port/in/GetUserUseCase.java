package com.example.subscriptions.domain.port.in;

import com.example.subscriptions.domain.model.User;

import java.util.List;

public interface GetUserUseCase {

    List<User> getAllUsers();

    User getUserById(Long id);

    User getUserByLogin(String login);
}
