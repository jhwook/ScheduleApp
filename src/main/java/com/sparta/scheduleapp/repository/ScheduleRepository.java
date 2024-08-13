package com.sparta.scheduleapp.repository;

import com.sparta.scheduleapp.entity.Schedule;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Objects;

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
                return schedule;
            } else {
                return null;
            }
        }, id);
    }


}
