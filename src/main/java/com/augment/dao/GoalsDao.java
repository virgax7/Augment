package com.augment.dao;

import com.augment.vo.schedule.Goal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ColumnMapRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Component
public class GoalsDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public GoalsDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void createGoal(final String username, final String title, final String description, final Date startDate, final Date targetDate) {
        jdbcTemplate.update("INSERT INTO goal (username, title, description, start_date, target_date) VALUES " +
                        "(?,?,?,?,?)", username, title, description,
                startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                targetDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
    }

    public List<Map<String, Object>> getGoal(final String username, final Goal.STATUS goalStatus) {
        return jdbcTemplate.query("SELECT * FROM goal WHERE Username='" + username + "' AND status='" + goalStatus.toString() + "' AND archived=" + false, getRowMapper());
    }

    public List<Map<String, Object>> getGoal(final String username, final String title) {
        return jdbcTemplate.queryForList("SELECT * FROM goal WHERE username=? AND title=?", username, title);
    }

    public void updateGoalStatus(final String username, final String title, final String status) {
        jdbcTemplate.update("UPDATE goal SET status=? WHERE username=? AND title=?", status, username, title);
    }

    public void updateGoal(final String username, final String oldTitle, final String newTitle, final String description,
                           final Date startDate, final Date targetDate, final String status, final boolean archived) {
        jdbcTemplate.update("UPDATE goal SET title=?, description=?, start_date=?, target_date=?, status=?, archived=? WHERE " +
                        "username=? AND title=?", newTitle, description, startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                targetDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), status, archived, username, oldTitle);
    }

    private RowMapper getRowMapper() {
        return new ColumnMapRowMapper() {
            protected Map<String, Object> createColumnMap(int columnCount) {
                return new LinkedHashMap<>(columnCount);
            }
        };
    }
}
