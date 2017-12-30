package com.augment.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class LoginDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Map<String, Object>> getUser(final String username, final String password) {
        return jdbcTemplate.queryForList("select * from users where username=? and password=?", username, password);
    }
}
