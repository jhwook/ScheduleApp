package com.sparta.scheduleapp.service;

import com.sparta.scheduleapp.dto.ScheduleRequestDto;
import com.sparta.scheduleapp.dto.ScheduleResponseDto;
import com.sparta.scheduleapp.entity.Schedule;
import com.sparta.scheduleapp.repository.ScheduleRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

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

    public List<ScheduleResponseDto> getSchdules(String username, String updatedAt) {
        ScheduleRepository scheduleRepository = new ScheduleRepository(jdbcTemplate);
        List<ScheduleResponseDto> scheduleList = new ArrayList<>();
        if(username != null && updatedAt != null) {
            scheduleList = scheduleRepository.getAllByUsernameAndUpdatedAt(username, updatedAt);
        } else if(username != null) {
            scheduleList = scheduleRepository.getAllByUsername(username);
        } else if(updatedAt != null) {
            scheduleList = scheduleRepository.getAllByUpdatedAt(updatedAt);
        }

        return scheduleList;
    }

    public ScheduleResponseDto updateSchedule(Long id, ScheduleRequestDto scheduleRequestDto) {
        ScheduleRepository scheduleRepository = new ScheduleRepository(jdbcTemplate);

        Schedule exist = scheduleRepository.findOne(id);
        if(exist != null) {
            boolean checkPassword = scheduleRepository.checkPassword(id, scheduleRequestDto.getPassword());

            if(checkPassword) {
                ScheduleResponseDto updatedSchedule = scheduleRepository.updateSchedule(id, scheduleRequestDto);
                return updatedSchedule;
            } else {
                throw new IllegalArgumentException("비밀번호가 틀렸습니다.");
            }
        } else {
            throw new IllegalArgumentException("해당 스케줄은는 존재하지 않습니다.");
        }


    }

    public String deleteSchedule(Long id, ScheduleRequestDto scheduleRequestDto) {
        ScheduleRepository scheduleRepository = new ScheduleRepository(jdbcTemplate);
        Schedule exist = scheduleRepository.findOne(id);

        if(exist != null) {
            boolean checkPassword = scheduleRepository.checkPassword(id, scheduleRequestDto.getPassword());

            if(checkPassword) {
                scheduleRepository.deleteSchedule(id);
                return "성공적으로 삭제 되었습니다.";
            } else {
                throw new IllegalArgumentException("비밀번호가 틀렸습니다.");
            }
        } else {
            throw new IllegalArgumentException("해당 스케줄은는 존재하지 않습니다.");
        }
    }
}
