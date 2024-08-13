package com.sparta.scheduleapp.service;

import com.sparta.scheduleapp.dto.UserRequestDto;
import com.sparta.scheduleapp.dto.UserResponseDto;
import com.sparta.scheduleapp.entity.User;
import com.sparta.scheduleapp.repository.UserRepository;
import org.springframework.jdbc.core.JdbcTemplate;

public class UserService {
    private final JdbcTemplate jdbcTemplate;

    public UserService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public UserResponseDto createUser(UserRequestDto userRequestDto) {
        User user = new User(userRequestDto);

        UserRepository userRepository = new UserRepository(jdbcTemplate);
        User saveUser = userRepository.save(user);

        UserResponseDto userResponseDto = new UserResponseDto(user);

        return userResponseDto;
    }
}
