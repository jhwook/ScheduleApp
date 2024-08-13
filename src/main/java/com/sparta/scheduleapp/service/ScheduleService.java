package com.sparta.scheduleapp.service;

import com.sparta.scheduleapp.dto.ScheduleRequestDto;
import com.sparta.scheduleapp.dto.ScheduleResponseDto;
import com.sparta.scheduleapp.entity.Schedule;
import com.sparta.scheduleapp.repository.ScheduleRepository;
import org.springframework.jdbc.core.JdbcTemplate;

public class ScheduleService {
    private final JdbcTemplate jdbcTemplate;

    public ScheduleService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public ScheduleResponseDto createSchedule (ScheduleRequestDto scheduleRequestDto) {
        Schedule schedule = new Schedule(scheduleRequestDto);

        ScheduleRepository scheduleRepository = new ScheduleRepository(jdbcTemplate);
        scheduleRepository.save(schedule);

        ScheduleResponseDto scheduleResponseDto = new ScheduleResponseDto(schedule);

        return scheduleResponseDto;
    }

    public ScheduleResponseDto findOneSchedule(Long id) {
        ScheduleRepository scheduleRepository = new ScheduleRepository(jdbcTemplate);

        Schedule findSchedule = scheduleRepository.findOne(id);
        Schedule schedule = new Schedule(findSchedule);

        ScheduleResponseDto scheduleResponseDto = new ScheduleResponseDto(schedule);

        return scheduleResponseDto;
    }
}
