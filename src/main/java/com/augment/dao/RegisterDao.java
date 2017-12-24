package com.augment.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class RegisterDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public void signUp(final String username, final String password) {
        jdbcTemplate.update("insert into users values (?,?)", new Object[] {username, password});
    }

    public boolean usernameNotTaken(final String username) {
        return jdbcTemplate.queryForList("select * from users where username=?", new Object[]{username}).size() == 0;
    }
}
