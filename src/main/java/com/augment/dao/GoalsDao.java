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
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void createGoal(final String username, final String title, final String description, final Date startDate, final Date targetDate) {
        jdbcTemplate.update("INSERT INTO goal (username, title, description, start_date, target_date) VALUES " +
                "(?,?,?,?,?)", username, title, description,
                startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                targetDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
    }

    public List<Map<String, Object>> getGoal(final String username, final Goal.STATUS goalStatus) {
        return jdbcTemplate.query("select * from goal where username='" + username + "' and status='" + goalStatus.toString() + "' and archived=" + false, getRowMapper());
    }

    public List<Map<String, Object>> getGoal(final String username, final String title) {
        return jdbcTemplate.queryForList("SELECT * FROM goal WHERE username=? AND title=?", username, title);
    }

    private RowMapper getRowMapper() {
        return new ColumnMapRowMapper() {
            protected Map<String, Object> createColumnMap(int columnCount) {
                return new LinkedHashMap<>(columnCount);
            }
        };
    }
}