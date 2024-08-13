package com.sparta.scheduleapp.entity;

import com.sparta.scheduleapp.dto.ScheduleRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Schedule {
    private Long id;

    private String content;

    private String password;

    private String username;

    private String createdAt;

    private String updatedAt;

    public Schedule (ScheduleRequestDto scheduleRequestDto) {
        this.content = scheduleRequestDto.getContent();
        this.username = scheduleRequestDto.getUsername();
        this.password = scheduleRequestDto.getPassword();
    }

    public Schedule(Schedule schedule) {
        this.id = schedule.getId();
        this.content = schedule.getContent();
        this.username = schedule.getUsername();
        this.password = schedule.getPassword();
        this.createdAt = schedule.getCreatedAt();
        this.updatedAt = schedule.getUpdatedAt();
    }

}
