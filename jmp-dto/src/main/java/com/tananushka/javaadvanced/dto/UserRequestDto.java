package com.tananushka.javaadvanced.dto;

public record UserRequestDto(
        Long id,
        String name,
        String surname,
        String birthday
) {
}
