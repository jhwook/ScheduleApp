package com.sparta.scheduleapp.controller;

import com.sparta.scheduleapp.dto.UserRequestDto;
import com.sparta.scheduleapp.dto.UserResponseDto;
import com.sparta.scheduleapp.service.UserService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    private final JdbcTemplate jdbcTemplate;

    public UserController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostMapping("/create")
    public UserResponseDto createUser (@RequestBody UserRequestDto userRequestDto) {
        UserService userService = new UserService(jdbcTemplate);
        return userService.createUser(userRequestDto);
    }
}
