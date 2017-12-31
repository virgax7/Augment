package com.augment.dao;

import com.augment.backing.beans.TodaySchedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ColumnMapRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Component
public class TodayScheduleDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public TodayScheduleDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void updateDay(final TodaySchedule todaySchedule, final String username) {
        jdbcTemplate.update("UPDATE day SET four_five_am=?, five_six_am=?, six_seven_am=?, seven_eight_am=?, eight_nine_am=?, nine_ten_am=?" +
                ", ten_eleven_am=?, eleven_twelve_pm=?, twelve_one_pm=?, one_two_pm=?, two_three_pm=?, three_four_pm=?, four_five_pm=?" +
                ", five_six_pm=?, six_seven_pm=?, seven_eight_pm=?, eight_nine_pm=?, nine_ten_pm=?, ten_eleven_pm=?, eleven_twelve_am=?" +
                ", twelve_one_am=?, one_two_am=?, two_three_am=?, three_four_am=? WHERE username=?", todaySchedule.getFourFiveAM(),
                todaySchedule.getFiveSixAM(), todaySchedule.getSixSevenAM(), todaySchedule.getSevenEightAM(), todaySchedule.getEightNineAM(), todaySchedule.getNineTenAM(),
                todaySchedule.getTenElevenAM(), todaySchedule.getElevenTwelvePM(), todaySchedule.getTwelveOnePM(), todaySchedule.getOneTwoPM(), todaySchedule.getTwoThreePM(),
                todaySchedule.getThreeFourPM(), todaySchedule.getFourFivePM(), todaySchedule.getFiveSixPM(), todaySchedule.getSixSevenPM(), todaySchedule.getSevenEightPM(),
                todaySchedule.getEightNinePM(), todaySchedule.getNineTenPM(), todaySchedule.getTenElevenPM(), todaySchedule.getElevenTwelveAM(), todaySchedule.getTwelveOneAM(),
                todaySchedule.getOneTwoAM(), todaySchedule.getTwoThreeAM(), todaySchedule.getThreeFourAM(), username);
    }

    public void createDay(final String username) {
        jdbcTemplate.update("insert into day (username) values (?)", username);
    }

    public List<Map<String, Object>> getDay(final String username) {
        return jdbcTemplate.query("select * from day where username='" + username + "'", new ColumnMapRowMapper() {
            protected Map<String, Object> createColumnMap(int columnCount) {
                return new LinkedHashMap<>(columnCount);
            }
        });
    }
}
