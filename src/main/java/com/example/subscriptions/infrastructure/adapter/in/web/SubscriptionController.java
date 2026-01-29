package com.example.subscriptions.infrastructure.adapter.in.web;

import com.example.subscriptions.domain.model.SubscriptionStatus;
import com.example.subscriptions.domain.port.in.*;
import com.example.subscriptions.infrastructure.adapter.in.web.dto.SubscriptionRequest;
import com.example.subscriptions.infrastructure.adapter.in.web.dto.SubscriptionResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subscriptions")
public class SubscriptionController {

    private final CreateSubscriptionUseCase createSubscriptionUseCase;
    private final GetSubscriptionUseCase getSubscriptionUseCase;
    private final UpdateSubscriptionUseCase updateSubscriptionUseCase;
    private final DeleteSubscriptionUseCase deleteSubscriptionUseCase;
    private final ManageSubscriptionStatusUseCase manageSubscriptionStatusUseCase;

    public SubscriptionController(
            CreateSubscriptionUseCase createSubscriptionUseCase,
            GetSubscriptionUseCase getSubscriptionUseCase,
            UpdateSubscriptionUseCase updateSubscriptionUseCase,
            DeleteSubscriptionUseCase deleteSubscriptionUseCase,
            ManageSubscriptionStatusUseCase manageSubscriptionStatusUseCase) {
        this.createSubscriptionUseCase = createSubscriptionUseCase;
        this.getSubscriptionUseCase = getSubscriptionUseCase;
        this.updateSubscriptionUseCase = updateSubscriptionUseCase;
        this.deleteSubscriptionUseCase = deleteSubscriptionUseCase;
        this.manageSubscriptionStatusUseCase = manageSubscriptionStatusUseCase;
    }

    @GetMapping
    public ResponseEntity<List<SubscriptionResponse>> getAllSubscriptions() {
        var subscriptions = getSubscriptionUseCase.getAllSubscriptions().stream()
                .map(SubscriptionResponse::fromDomain)
                .toList();
        return ResponseEntity.ok(subscriptions);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubscriptionResponse> getSubscriptionById(@PathVariable Long id) {
        var subscription = getSubscriptionUseCase.getSubscriptionById(id);
        return ResponseEntity.ok(SubscriptionResponse.fromDomain(subscription));
    }

    @GetMapping("/user/{email}")
    public ResponseEntity<List<SubscriptionResponse>> getSubscriptionsByUserEmail(@PathVariable String email) {
        var subscriptions = getSubscriptionUseCase.getSubscriptionsByUserEmail(email).stream()
                .map(SubscriptionResponse::fromDomain)
                .toList();
        return ResponseEntity.ok(subscriptions);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<SubscriptionResponse>> getSubscriptionsByStatus(@PathVariable SubscriptionStatus status) {
        var subscriptions = getSubscriptionUseCase.getSubscriptionsByStatus(status).stream()
                .map(SubscriptionResponse::fromDomain)
                .toList();
        return ResponseEntity.ok(subscriptions);
    }

    @PostMapping
    public ResponseEntity<SubscriptionResponse> createSubscription(@Valid @RequestBody SubscriptionRequest request) {
        var command = new CreateSubscriptionUseCase.CreateSubscriptionCommand(
                request.userEmail(),
                request.planName(),
                request.price(),
                request.status(),
                request.startDate(),
                request.endDate()
        );
        var subscription = createSubscriptionUseCase.createSubscription(command);
        return ResponseEntity.status(HttpStatus.CREATED).body(SubscriptionResponse.fromDomain(subscription));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SubscriptionResponse> updateSubscription(
            @PathVariable Long id,
            @Valid @RequestBody SubscriptionRequest request) {
        var command = new UpdateSubscriptionUseCase.UpdateSubscriptionCommand(
                request.userEmail(),
                request.planName(),
                request.price(),
                request.status(),
                request.startDate(),
                request.endDate()
        );
        var subscription = updateSubscriptionUseCase.updateSubscription(id, command);
        return ResponseEntity.ok(SubscriptionResponse.fromDomain(subscription));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSubscription(@PathVariable Long id) {
        deleteSubscriptionUseCase.deleteSubscription(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/cancel")
    public ResponseEntity<SubscriptionResponse> cancelSubscription(@PathVariable Long id) {
        var subscription = manageSubscriptionStatusUseCase.cancelSubscription(id);
        return ResponseEntity.ok(SubscriptionResponse.fromDomain(subscription));
    }

    @PatchMapping("/{id}/pause")
    public ResponseEntity<SubscriptionResponse> pauseSubscription(@PathVariable Long id) {
        var subscription = manageSubscriptionStatusUseCase.pauseSubscription(id);
        return ResponseEntity.ok(SubscriptionResponse.fromDomain(subscription));
    }

    @PatchMapping("/{id}/activate")
    public ResponseEntity<SubscriptionResponse> activateSubscription(@PathVariable Long id) {
        var subscription = manageSubscriptionStatusUseCase.activateSubscription(id);
        return ResponseEntity.ok(SubscriptionResponse.fromDomain(subscription));
    }
}
