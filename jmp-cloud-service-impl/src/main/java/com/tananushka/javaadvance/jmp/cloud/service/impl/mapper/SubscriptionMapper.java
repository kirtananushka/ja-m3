package com.tananushka.javaadvance.jmp.cloud.service.impl.mapper;

import com.tananushka.javaadvance.jmp.cloud.service.impl.entity.Subscription;
import com.tananushka.javaadvance.jmp.cloud.service.impl.entity.User;
import com.tananushka.javaadvance.jmp.dto.SubscriptionRequestDto;
import com.tananushka.javaadvance.jmp.dto.SubscriptionResponseDto;
import com.tananushka.javaadvance.jmp.dto.UserRequestDto;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.time.LocalDate;

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