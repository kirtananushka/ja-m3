package com.tananushka.javaadvance.jmp.service.rest.controller;


import com.tananushka.javaadvance.jmp.dto.SubscriptionResponseDto;
import com.tananushka.javaadvance.jmp.dto.SubscriptionRequestDto;
import com.tananushka.javaadvance.jmp.service.api.SubscriptionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subscriptions")
@AllArgsConstructor
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    @PostMapping
    public ResponseEntity<SubscriptionResponseDto> createSubscription(@RequestBody SubscriptionRequestDto request) {
        SubscriptionResponseDto response = subscriptionService.createSubscription(request);
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(201));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SubscriptionResponseDto> updateSubscription(@PathVariable("id") Long id, @RequestBody SubscriptionRequestDto subscriptionRequestDto) {
        return ResponseEntity.ok(subscriptionService.updateSubscription(id, subscriptionRequestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSubscription(@PathVariable Long id) {
        subscriptionService.deleteSubscription(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubscriptionResponseDto> getSubscription(@PathVariable Long id) {
        return ResponseEntity.ok(subscriptionService.getSubscription(id));
    }

    @GetMapping
    public ResponseEntity<List<SubscriptionResponseDto>> getAllSubscriptions() {
        return ResponseEntity.ok(subscriptionService.getAllSubscriptions());
    }
}
