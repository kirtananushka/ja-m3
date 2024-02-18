package com.tananushka.javaadvance.jmp.cloud.service.impl.service;


import com.tananushka.javaadvance.jmp.cloud.service.impl.entity.Subscription;
import com.tananushka.javaadvance.jmp.cloud.service.impl.entity.User;
import com.tananushka.javaadvance.jmp.cloud.service.impl.mapper.SubscriptionMapper;
import com.tananushka.javaadvance.jmp.cloud.service.impl.repository.SubscriptionRepository;
import com.tananushka.javaadvance.jmp.cloud.service.impl.repository.UserRepository;
import com.tananushka.javaadvance.jmp.dto.SubscriptionRequestDto;
import com.tananushka.javaadvance.jmp.dto.SubscriptionResponseDto;
import com.tananushka.javaadvance.jmp.service.api.SubscriptionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {

    private final SubscriptionMapper subscriptionMapper;
    private final SubscriptionRepository subscriptionRepository;
    private final UserRepository userRepository;


    @Override
    public SubscriptionResponseDto createSubscription(SubscriptionRequestDto subscriptionRequestDto) {
        return Optional.of(subscriptionRequestDto)
                .map(this::prepareSubscriptionEntity)
                .map(subscriptionRepository::save)
                .map(subscriptionMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Unable to create subscription from subscriptionRequestDto=" + subscriptionRequestDto));
    }

    @Override
    public SubscriptionResponseDto updateSubscription(Long id, SubscriptionRequestDto subscriptionRequestDto) {
        return Optional.of(new SubscriptionRequestDto(id, subscriptionRequestDto.userId()))
                .map(this::updateSubscriptionEntity)
                .map(subscriptionRepository::save)
                .map(subscriptionMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Subscription not found with ID=" + id));
    }

    @Override
    public void deleteSubscription(Long id) {

    }

    @Override
    public SubscriptionResponseDto getSubscription(Long id) {
        return null;
    }

    @Override
    public List<SubscriptionResponseDto> getAllSubscriptions() {
        return null;
    }

    private Subscription prepareSubscriptionEntity(SubscriptionRequestDto subscriptionRequestDto) {
        User user = fetchUserForSubscription(subscriptionRequestDto);
        return prepareSubscriptionWithUser(subscriptionRequestDto, user);
    }

    private Subscription updateSubscriptionEntity(SubscriptionRequestDto subscriptionRequestDto) {
        Subscription existingSubscription = fetchSubscription(subscriptionRequestDto.id());
        User user = fetchUserForSubscription(subscriptionRequestDto);
        existingSubscription.setUser(user);
        return existingSubscription;
    }

    private Subscription prepareSubscriptionWithUser(SubscriptionRequestDto subscriptionRequestDto, User user) {
        Subscription subscription = subscriptionMapper.toEntity(subscriptionRequestDto);
        subscription.setUser(user);
        return subscription;
    }

    private User fetchUserForSubscription(SubscriptionRequestDto subscriptionRequestDto) {
        return userRepository.findById(subscriptionRequestDto.userId())
                .orElseThrow(() -> new RuntimeException("User not found with ID=" + subscriptionRequestDto.userId()));
    }

    private Subscription fetchSubscription(Long id) {
        return subscriptionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Subscription not found with ID=" + id));
    }
}