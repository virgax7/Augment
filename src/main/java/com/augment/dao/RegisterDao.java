package com.augment.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class RegisterDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public RegisterDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void createUser(final String username, final String password, final String email) {
        jdbcTemplate.update("insert into users values (?,?,?)", username, password, email);
    }

    public List<Map<String, Object>> getUser(final String username) {
        return jdbcTemplate.queryForList("select * from users where username=?", username);
    }

    public List<Map<String, Object>> getUserFromEmail(final String email) {
        return jdbcTemplate.queryForList("select * from users where email=?", email);
    }
}
