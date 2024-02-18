package com.tananushka.javaadvance.jmp.dto;

public record UserRequestDto(
        Long id,
        String name,
        String surname,
        String birthday
) {
}
