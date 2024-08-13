package com.sparta.scheduleapp.controller;

import com.sparta.scheduleapp.dto.ScheduleRequestDto;
import com.sparta.scheduleapp.dto.ScheduleResponseDto;
import com.sparta.scheduleapp.service.ScheduleService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {
    private final JdbcTemplate jdbcTemplate;

    public ScheduleController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // 스케줄 생성
    @PostMapping("/create")
    public ScheduleResponseDto createSchedule(@RequestBody ScheduleRequestDto scheduleRequestDto) {
        ScheduleService scheduleService = new ScheduleService(jdbcTemplate);
        return scheduleService.createSchedule(scheduleRequestDto);
    }

    // id로 스케줄 쿼리
    @GetMapping("/{id}")
    public ScheduleResponseDto findOneSchedule(@PathVariable Long id) {
        ScheduleService scheduleService = new ScheduleService(jdbcTemplate);
        return scheduleService.findOneSchedule(id);
    }

    // username 및 updatedAt 으로 스케줄 쿼리
    @GetMapping("/schedules/param")
    public List<ScheduleResponseDto> getSchedules(@RequestParam(required = false) String username, @RequestParam(required = false) String updatedAt) {
        ScheduleService scheduleService = new ScheduleService(jdbcTemplate);
        return scheduleService.getSchdules(username, updatedAt);
    }

    // 선택 일정 수정
    @PutMapping("/update/{id}")
    public ScheduleResponseDto updateSchedule(@PathVariable Long id, @RequestBody ScheduleRequestDto scheduleRequestDto) {
        ScheduleService scheduleService = new ScheduleService(jdbcTemplate);
        return scheduleService.updateSchedule(id, scheduleRequestDto);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteSchedule(@PathVariable Long id, @RequestBody ScheduleRequestDto scheduleRequestDto ) {
        ScheduleService scheduleService = new ScheduleService(jdbcTemplate);
        return scheduleService.deleteSchedule(id, scheduleRequestDto);
    }
}
