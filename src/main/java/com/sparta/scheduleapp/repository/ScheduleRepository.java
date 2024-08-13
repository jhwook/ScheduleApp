package com.sparta.scheduleapp.repository;

import com.sparta.scheduleapp.dto.ScheduleRequestDto;
import com.sparta.scheduleapp.dto.ScheduleResponseDto;
import com.sparta.scheduleapp.entity.Schedule;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ScheduleRepository {
    private final JdbcTemplate jdbcTemplate;

    public ScheduleRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Schedule save(Schedule schedule) {

        String sql = "INSERT INTO schedule (content, username, password) VALUES (?, ?, ?)";
        jdbcTemplate.update(con -> {
            PreparedStatement preparedStatement = con.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, schedule.getContent());
            preparedStatement.setString(2, schedule.getUsername());
            preparedStatement.setString(3, schedule.getPassword());

            return preparedStatement;
        });


        return schedule;
    }

    public Schedule findOne(Long id) {
        String sql = "SELECT * from schedule where id=?";

        return jdbcTemplate.query(sql, resultSet -> {
            if(resultSet.next()) {
                Schedule schedule = new Schedule();
                schedule.setId(resultSet.getLong("id"));
                schedule.setContent(resultSet.getString("content"));
                schedule.setUsername(resultSet.getString("username"));
                schedule.setPassword(resultSet.getString("password"));
                schedule.setUpdatedAt(resultSet.getString("updated_at"));
                return schedule;
            } else {
                return null;
            }
        }, id);
    }


    public List<ScheduleResponseDto> getAllByUsernameAndUpdatedAt(String username, String updatedAt) {
        String sql = "SELECT * from schedule where username=? and updated_at=? order by updated_at DESC";

        return jdbcTemplate.query(sql, new Object[]{username, updatedAt}, (rs, rowNum) -> {
            // SQL 의 결과로 받아온 Memo 데이터들을 MemoResponseDto 타입으로 변환해줄 메서드
            Long id = rs.getLong("id");
            String username1 = rs.getString("username");
            String content = rs.getString("content");
            String updatedAt1 = rs.getString("updated_at");
            return new ScheduleResponseDto(id, username1, content, updatedAt1);
        });

    }

    public List<ScheduleResponseDto> getAllByUsername(String username) {
        String sql = "SELECT * from schedule where username=? order by updated_at DESC";


        return jdbcTemplate.query(sql, new Object[]{username}, (rs, rowNum) -> {
            // 결과 집합에서 데이터를 추출하고 ScheduleResponseDto 객체로 매핑
            Long id = rs.getLong("id");
            String username1 = rs.getString("username");
            String content = rs.getString("content");
            String updatedAt = rs.getString("updated_at");

            return new ScheduleResponseDto(id, username1, content, updatedAt);
        });
    }

    public List<ScheduleResponseDto> getAllByUpdatedAt(String updatedAt) {
        String sql = "SELECT * from schedule where updated_at=? order by updated_at DESC";

        return jdbcTemplate.query(sql,new Object[]{updatedAt}, (rs, rowNum) -> {
            // SQL 의 결과로 받아온 Memo 데이터들을 MemoResponseDto 타입으로 변환해줄 메서드
            Long id = rs.getLong("id");
            String username = rs.getString("username");
            String content = rs.getString("content");
            String updatedAt1 = rs.getString("updated_at");
            return new ScheduleResponseDto(id, username, content, updatedAt1);
        });
    }



    public boolean checkPassword(Long id, String password) {
        String sql = "SELECT password from schedule where id=?";

        return Boolean.TRUE.equals(jdbcTemplate.query(sql, resultSet -> {
            if (resultSet.next()) {
                String findPassword = resultSet.getString("password");
                if (password.equals(findPassword)) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return null;
            }
        }, id));
    }

    public ScheduleResponseDto updateSchedule(Long id, ScheduleRequestDto scheduleRequestDto) {
        String sql = "UPDATE schedule SET username = ?, content = ? WHERE id = ?";
        jdbcTemplate.update(sql, scheduleRequestDto.getUsername(), scheduleRequestDto.getContent(), id);

        Schedule updatedSchedule = findOne(id);

        // 조회된 Schedule 객체를 ScheduleResponseDto로 변환하여 반환
        if (updatedSchedule != null) {
            return new ScheduleResponseDto(
                    updatedSchedule.getId(),
                    updatedSchedule.getUsername(),
                    updatedSchedule.getContent(),
                    updatedSchedule.getUpdatedAt() // updatedAt을 필요에 따라 추가하세요
            );
        } else {
            throw new IllegalArgumentException("업데이트 되지 않음");
        }
    }

    public void deleteSchedule(Long id) {
        String sql = "DELETE FROM schedule WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
