package com.sparta.scheduleapp.dto;

import com.sparta.scheduleapp.entity.Schedule;
import lombok.Getter;

@Getter
public class ScheduleResponseDto {
    private Long id;
    private String content;

    private String username;
    public ScheduleResponseDto(Schedule schedule) {
        this.id = schedule.getId();
        this.content = schedule.getContent();
        this.username = schedule.getUsername();
    }
}
