package com.tananushka.javaadvanced.dto;

import lombok.Value;
import org.springframework.hateoas.RepresentationModel;

@Value
public class UserResponseDto extends RepresentationModel<UserResponseDto> {
    Long id;
    String name;
    String surname;
    String birthday;
}