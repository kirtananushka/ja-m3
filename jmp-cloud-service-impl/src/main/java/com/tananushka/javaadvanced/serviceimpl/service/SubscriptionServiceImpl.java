package com.tananushka.javaadvanced.serviceimpl.service;

import com.tananushka.javaadvanced.dto.SubscriptionRequestDto;
import com.tananushka.javaadvanced.dto.SubscriptionResponseDto;
import com.tananushka.javaadvanced.exception.JmpException;
import com.tananushka.javaadvanced.serviceapi.SubscriptionService;
import com.tananushka.javaadvanced.serviceimpl.entity.Subscription;
import com.tananushka.javaadvanced.serviceimpl.entity.User;
import com.tananushka.javaadvanced.serviceimpl.mapper.SubscriptionMapper;
import com.tananushka.javaadvanced.serviceimpl.repository.SubscriptionRepository;
import com.tananushka.javaadvanced.serviceimpl.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {

    private final SubscriptionMapper subscriptionMapper;
    private final SubscriptionRepository subscriptionRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public SubscriptionResponseDto createSubscription(SubscriptionRequestDto subscriptionRequestDto) {
        return Optional.of(subscriptionRequestDto)
                .map(this::prepareSubscriptionEntity)
                .map(subscriptionRepository::save)
                .map(subscriptionMapper::toDto)
                .orElseThrow(() -> new JmpException("Unable to create subscription from subscriptionRequestDto=" + subscriptionRequestDto, "500"));
    }

    @Override
    @Transactional
    public SubscriptionResponseDto updateSubscription(Long id, SubscriptionRequestDto subscriptionRequestDto) {
        return Optional.of(new SubscriptionRequestDto(id, subscriptionRequestDto.userId()))
                .map(this::updateSubscriptionEntity)
                .map(subscriptionRepository::save)
                .map(subscriptionMapper::toDto)
                .orElseThrow(() -> new JmpException("Subscription not found with ID=" + id, "404"));
    }

    @Override
    @Transactional(readOnly = true)
    public SubscriptionResponseDto getSubscription(Long id) {
        return subscriptionRepository.findById(id)
                .map(subscriptionMapper::toDto)
                .orElseThrow(() -> new JmpException("Subscription not found with ID=" + id, "404"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<SubscriptionResponseDto> getAllSubscriptions() {
        return subscriptionRepository.findAll().stream()
                .map(subscriptionMapper::toDto)
                .toList();
    }

    @Override
    @Transactional
    public void deleteSubscription(Long id) {
        subscriptionRepository.findById(id)
                .ifPresentOrElse(subscriptionRepository::delete,
                        () -> {
                            throw new JmpException("Subscription not found with ID=" + id, "404");
                        });
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
                .orElseThrow(() -> new JmpException("User not found with ID=" + subscriptionRequestDto.userId(), "400"));
    }

    private Subscription fetchSubscription(Long id) {
        return subscriptionRepository.findById(id)
                .orElseThrow(() -> new JmpException("Subscription not found with ID=" + id, "404"));
    }
}