package com.tananushka.javaadvanced.serviceimpl.mapper;

import com.tananushka.javaadvanced.dto.SubscriptionRequestDto;
import com.tananushka.javaadvanced.dto.SubscriptionResponseDto;
import com.tananushka.javaadvanced.serviceimpl.entity.Subscription;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SubscriptionMapper {

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "startDate", target = "startDate", dateFormat = "yyyy-MM-dd HH:mm:ss")
    SubscriptionResponseDto toDto(Subscription subscription);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "startDate", ignore = true)
    Subscription toEntity(SubscriptionRequestDto dto);
}