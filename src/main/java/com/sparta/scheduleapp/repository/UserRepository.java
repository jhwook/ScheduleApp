package com.sparta.scheduleapp.repository;

import com.sparta.scheduleapp.dto.UserRequestDto;
import com.sparta.scheduleapp.entity.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
import java.sql.Statement;

public class UserRepository {
    private final JdbcTemplate jdbcTemplate;

    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public User save(User user) {

        String sql = "INSERT INTO user (username, email) VALUES (?, ?)";

        jdbcTemplate.update(con -> {
            PreparedStatement preparedStatement = con.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getEmail());

            return preparedStatement;
        });

        return user;
    }
}
