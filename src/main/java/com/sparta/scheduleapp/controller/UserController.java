package com.sparta.scheduleapp.controller;

import com.sparta.scheduleapp.dto.UserRequestDto;
import com.sparta.scheduleapp.dto.UserResponseDto;
import com.sparta.scheduleapp.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(JdbcTemplate jdbcTemplate) {
        this.userService = new UserService(jdbcTemplate);
    }

    @PostMapping("/create")
    public ResponseEntity<UserResponseDto> createUser (@RequestBody UserRequestDto userRequestDto) {
        UserResponseDto res = userService.createUser(userRequestDto);
        return ResponseEntity.ok(res);
    }
}
