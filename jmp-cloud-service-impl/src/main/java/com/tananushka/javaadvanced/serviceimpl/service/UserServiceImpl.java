package com.tananushka.javaadvanced.serviceimpl.service;

import com.tananushka.javaadvanced.dto.UserRequestDto;
import com.tananushka.javaadvanced.dto.UserResponseDto;
import com.tananushka.javaadvanced.exception.JmpException;
import com.tananushka.javaadvanced.serviceapi.UserService;
import com.tananushka.javaadvanced.serviceimpl.entity.User;
import com.tananushka.javaadvanced.serviceimpl.mapper.UserMapper;
import com.tananushka.javaadvanced.serviceimpl.repository.SubscriptionRepository;
import com.tananushka.javaadvanced.serviceimpl.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final SubscriptionRepository subscriptionRepository;

    @Override
    @Transactional
    public UserResponseDto createUser(UserRequestDto userRequestDto) {
        return Optional.of(userRequestDto)
                .map(userMapper::toEntity)
                .map(userRepository::save)
                .map(userMapper::toDto)
                .orElseThrow(() -> new JmpException("Unable to create user from userRequestDto=" + userRequestDto, "500"));
    }

    @Override
    @Transactional
    public UserResponseDto updateUser(Long id, UserRequestDto userRequestDto) {
        return userRepository.findById(id)
                .map(existingUser -> updateUserWithDto(existingUser, userRequestDto))
                .map(userRepository::save)
                .map(userMapper::toDto)
                .orElseThrow(() -> new JmpException("User not found with ID=" + id, "404"));
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponseDto getUser(Long id) {
        return userRepository.findById(id)
                .map(userMapper::toDto)
                .orElseThrow(() -> new JmpException("User not found with ID=" + id, "404"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserResponseDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toDto)
                .toList();
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        userRepository.findById(id)
                .map(user -> {
                    subscriptionRepository.deleteAllByUser(user);
                    return user;
                })
                .ifPresentOrElse(
                        userRepository::delete,
                        () -> {
                            throw new JmpException("User not found with ID=" + id, "404");
                        }
                );
    }

    private User updateUserWithDto(User user, UserRequestDto userRequestDto) {
        userMapper.updateEntityFromDto(userRequestDto, user);
        return user;
    }
}