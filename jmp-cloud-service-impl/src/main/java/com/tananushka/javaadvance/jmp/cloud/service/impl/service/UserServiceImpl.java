package com.tananushka.javaadvance.jmp.cloud.service.impl.service;

import com.tananushka.javaadvance.jmp.cloud.service.impl.entity.Subscription;
import com.tananushka.javaadvance.jmp.cloud.service.impl.entity.User;
import com.tananushka.javaadvance.jmp.cloud.service.impl.mapper.UserMapper;
import com.tananushka.javaadvance.jmp.cloud.service.impl.repository.UserRepository;
import com.tananushka.javaadvance.jmp.dto.UserRequestDto;
import com.tananushka.javaadvance.jmp.dto.UserResponseDto;
import com.tananushka.javaadvance.jmp.service.api.UserService;
import lombok.AllArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;

    @Override
    public UserResponseDto createUser(UserRequestDto userRequestDto) {
        return Optional.of(userRequestDto)
                .map(userMapper::toEntity)
                .map(userRepository::save)
                .map(userMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Unable to create user from userRequestDto=" + userRequestDto));
    }

    @Override
    public UserResponseDto updateUser(Long id, UserRequestDto userRequestDto) {         
        return userRepository.findById(id)
                .map(existingUser -> updateUserWithDto(existingUser, userRequestDto))
                .map(userRepository::save)
                .map(userMapper::toDto)
                .orElseThrow(() -> new RuntimeException("User not found with ID=" + id));
    }

    private User updateUserWithDto(User user, UserRequestDto userRequestDto) {
        userMapper.updateEntityFromDto(userRequestDto, user);
        return user;
    }
    
    @Override
    public void deleteUser(Long id) {

    }

    @Override
    public UserResponseDto getUser(Long id) {
        return null;
    }

    @Override
    public List<UserResponseDto> getAllUsers() {
        return null;
    }
}