package com.sparta.scheduleapp.entity;

import com.sparta.scheduleapp.dto.UserRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class User {
    private Long id;
    private String username;

    public User(UserRequestDto userRequestDto) {
        this.username = userRequestDto.getUsername();
    }
}
