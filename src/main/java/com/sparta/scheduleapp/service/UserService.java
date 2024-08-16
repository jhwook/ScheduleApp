package com.sparta.scheduleapp.service;

import com.sparta.scheduleapp.dto.UserRequestDto;
import com.sparta.scheduleapp.dto.UserResponseDto;
import com.sparta.scheduleapp.entity.User;
import com.sparta.scheduleapp.repository.UserRepository;
import org.springframework.jdbc.core.JdbcTemplate;

public class UserService {
    private final UserRepository userRepository;

    public UserService(JdbcTemplate jdbcTemplate) {
        this.userRepository = new UserRepository(jdbcTemplate);
    }
    public UserResponseDto createUser(UserRequestDto userRequestDto) {
        User user = new User(userRequestDto);

        User saveUser = userRepository.save(user);

        UserResponseDto userResponseDto = new UserResponseDto(user);

        return userResponseDto;
    }
}
