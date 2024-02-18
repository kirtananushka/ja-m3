package com.tananushka.javaadvance.jmp.service.api;


import com.tananushka.javaadvance.jmp.dto.SubscriptionResponseDto;
import com.tananushka.javaadvance.jmp.dto.SubscriptionRequestDto;

import java.util.List;

public interface SubscriptionService {
    SubscriptionResponseDto createSubscription(SubscriptionRequestDto subscriptionRequestDto);

    SubscriptionResponseDto updateSubscription(Long id, SubscriptionRequestDto subscriptionRequestDto);

    void deleteSubscription(Long id);

    SubscriptionResponseDto getSubscription(Long id);

    List<SubscriptionResponseDto> getAllSubscriptions();
}
