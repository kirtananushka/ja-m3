package com.tananushka.javaadvanced.serviceimpl.mapper;

import com.tananushka.javaadvanced.dto.UserRequestDto;
import com.tananushka.javaadvanced.dto.UserResponseDto;
import com.tananushka.javaadvanced.serviceimpl.entity.User;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.time.LocalDate;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "subscriptions", ignore = true)
    @Mapping(target = "birthday", source = "birthday", dateFormat = "yyyy-MM-dd")
    User toEntity(UserRequestDto dto);

    @Mapping(target = "birthday", source = "birthday", dateFormat = "yyyy-MM-dd")
    UserResponseDto toDto(User entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", ignore = true)
    @Mapping(target = "surname", ignore = true)
    @Mapping(target = "birthday", ignore = true)
    @Mapping(target = "subscriptions", ignore = true)
    void updateEntityFromDto(UserRequestDto dto, @MappingTarget User entity);

    @AfterMapping
    default void updateUserFromDto(UserRequestDto dto, @MappingTarget User entity) {
        if (dto.name() != null) {
            entity.setName(dto.name());
        }

        if (dto.surname() != null) {
            entity.setSurname(dto.surname());
        }

        if (dto.birthday() != null) {
            LocalDate birthday = LocalDate.parse(dto.birthday());
            entity.setBirthday(birthday);
        }
    }
}