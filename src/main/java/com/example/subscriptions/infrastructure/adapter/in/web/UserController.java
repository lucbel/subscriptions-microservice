package com.example.subscriptions.infrastructure.adapter.in.web;

import com.example.subscriptions.domain.port.in.*;
import com.example.subscriptions.infrastructure.adapter.in.web.dto.UpdateUserRequest;
import com.example.subscriptions.infrastructure.adapter.in.web.dto.UserRequest;
import com.example.subscriptions.infrastructure.adapter.in.web.dto.UserResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final CreateUserUseCase createUserUseCase;
    private final GetUserUseCase getUserUseCase;
    private final UpdateUserUseCase updateUserUseCase;
    private final DeleteUserUseCase deleteUserUseCase;

    public UserController(
            CreateUserUseCase createUserUseCase,
            GetUserUseCase getUserUseCase,
            UpdateUserUseCase updateUserUseCase,
            DeleteUserUseCase deleteUserUseCase) {
        this.createUserUseCase = createUserUseCase;
        this.getUserUseCase = getUserUseCase;
        this.updateUserUseCase = updateUserUseCase;
        this.deleteUserUseCase = deleteUserUseCase;
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        var users = getUserUseCase.getAllUsers().stream()
                .map(UserResponse::fromDomain)
                .toList();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id) {
        var user = getUserUseCase.getUserById(id);
        return ResponseEntity.ok(UserResponse.fromDomain(user));
    }

    @GetMapping("/login/{login}")
    public ResponseEntity<UserResponse> getUserByLogin(@PathVariable String login) {
        var user = getUserUseCase.getUserByLogin(login);
        return ResponseEntity.ok(UserResponse.fromDomain(user));
    }

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserRequest request) {
        var command = new CreateUserUseCase.CreateUserCommand(
                request.name(),
                request.surname(),
                request.address(),
                request.login(),
                request.password()
        );
        var user = createUserUseCase.createUser(command);
        return ResponseEntity.status(HttpStatus.CREATED).body(UserResponse.fromDomain(user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(
            @PathVariable Long id,
            @Valid @RequestBody UpdateUserRequest request) {
        var command = new UpdateUserUseCase.UpdateUserCommand(
                request.name(),
                request.surname(),
                request.address(),
                request.login()
        );
        var user = updateUserUseCase.updateUser(id, command);
        return ResponseEntity.ok(UserResponse.fromDomain(user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        deleteUserUseCase.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
