package com.sparta.scheduleapp.dto;

import com.sparta.scheduleapp.entity.Schedule;
import lombok.Getter;

@Getter
public class ScheduleResponseDto {
    private Long id;
    private String content;
    private String username;
    private String createdAt;
    private String updatedAt;
    public ScheduleResponseDto(Schedule schedule) {
        this.id = schedule.getId();
        this.content = schedule.getContent();
        this.username = schedule.getUsername();
    }

    public ScheduleResponseDto(Long id, String username, String content) {
        this.id = id;
        this.content = content;
        this.username = username;
    }

    public ScheduleResponseDto(Long id, String username, String content, String createdAt, String updatedAt) {
        this.id = id;
        this.content = content;
        this.username = username;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
