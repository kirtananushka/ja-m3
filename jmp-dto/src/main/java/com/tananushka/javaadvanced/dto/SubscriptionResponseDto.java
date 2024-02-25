package com.tananushka.javaadvanced.dto;

import lombok.Value;
import org.springframework.hateoas.RepresentationModel;

@Value
public class SubscriptionResponseDto extends RepresentationModel<SubscriptionResponseDto> {
    Long id;
    Long userId;
    String startDate;
}