package com.sparta.scheduleapp.dto;

import com.sparta.scheduleapp.entity.User;
import lombok.Getter;

@Getter
public class UserResponseDto {
    private Long id;
    private String username;
    private String email;
    private String createdAt;
    private String updatedAt;

    public UserResponseDto(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
    }
}
