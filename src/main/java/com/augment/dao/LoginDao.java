package com.augment.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class LoginDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public boolean validate(final String username, final String password) {
        return jdbcTemplate.queryForList("select * from users where username=? and password=?", new Object[]{username, password}).size() == 1;
    }
}
