package com.tananushka.javaadvanced.serviceapi;

import com.tananushka.javaadvanced.dto.UserRequestDto;
import com.tananushka.javaadvanced.dto.UserResponseDto;

import java.util.List;

public interface UserService {
    UserResponseDto createUser(UserRequestDto userRequestDto);

    UserResponseDto updateUser(Long id, UserRequestDto userRequestDto);

    void deleteUser(Long id);

    UserResponseDto getUser(Long id);

    List<UserResponseDto> getAllUsers();
}
