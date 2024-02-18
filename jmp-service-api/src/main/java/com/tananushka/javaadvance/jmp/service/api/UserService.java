package com.tananushka.javaadvance.jmp.service.api;


import com.tananushka.javaadvance.jmp.dto.UserRequestDto;
import com.tananushka.javaadvance.jmp.dto.UserResponseDto;

import java.util.List;

public interface UserService {
    UserResponseDto createUser(UserRequestDto userRequestDto);

    UserResponseDto updateUser(Long id, UserRequestDto userRequestDto);

    void deleteUser(Long id);

    UserResponseDto getUser(Long id);

    List<UserResponseDto> getAllUsers();
}
