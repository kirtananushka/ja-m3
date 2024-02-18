package com.tananushka.javaadvance.jmp.dto;

public record UserResponseDto(
        Long id,
        String name,
        String surname,
        String birthday
) {
}
