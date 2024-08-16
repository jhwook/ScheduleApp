package com.sparta.scheduleapp.controller;

import com.sparta.scheduleapp.dto.ScheduleRequestDto;
import com.sparta.scheduleapp.dto.ScheduleResponseDto;
import com.sparta.scheduleapp.service.ScheduleService;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {
    private final ScheduleService scheduleService;

    public ScheduleController(JdbcTemplate jdbcTemplate) {
        this.scheduleService = new ScheduleService(jdbcTemplate);
    }

    // 스케줄 생성
    @PostMapping("/create")
    public ResponseEntity<ScheduleResponseDto> createSchedule(@RequestBody ScheduleRequestDto scheduleRequestDto) {
        ScheduleResponseDto res = scheduleService.createSchedule(scheduleRequestDto);
        return ResponseEntity.ok(res);
    }

    // id로 스케줄 쿼리
    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> findOneSchedule(@PathVariable Long id) {
        ScheduleResponseDto res = scheduleService.findOneSchedule(id);
        return ResponseEntity.ok(res);
    }

    // username 및 updatedAt 으로 스케줄 쿼리
    @GetMapping("/schedules/{offset}/{limit}/param")
    public ResponseEntity<List<ScheduleResponseDto>> getSchedules(@RequestParam(required = false) String username,
                                                  @RequestParam(required = false) String updatedAt,
                                                  @PathVariable Long offset, @PathVariable Long limit) {
        List<ScheduleResponseDto> res = scheduleService.getSchdules(username, updatedAt, offset, limit);
        return ResponseEntity.ok(res);
    }

    // 선택 일정 수정
    @PutMapping("/update/{id}")
    public ResponseEntity<ScheduleResponseDto> updateSchedule(@PathVariable Long id, @RequestBody ScheduleRequestDto scheduleRequestDto) {
        ScheduleResponseDto res = scheduleService.updateSchedule(id, scheduleRequestDto);
        return ResponseEntity.ok(res);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteSchedule(@PathVariable Long id, @RequestBody ScheduleRequestDto scheduleRequestDto ) {
        String res = scheduleService.deleteSchedule(id, scheduleRequestDto);
        return ResponseEntity.ok(res);
    }
}
