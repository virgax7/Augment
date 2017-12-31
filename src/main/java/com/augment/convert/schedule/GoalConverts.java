package com.augment.convert.schedule;

import com.augment.vo.schedule.Goal;

import java.util.Map;

public class GoalConverts {
    public static final Goal makeGoal(final Map<String, Object> goal) {
        return new Goal((String) goal.get("title"), (String) goal.get("description"),
                formatToLocalDate(goal.get("start_date").toString()), formatToLocalDate(goal.get("target_date").toString()),
                (String) goal.get("status"), (boolean) goal.get("archived"));
    }

    private static final String formatToLocalDate(final String dateString) {
        return dateString.replaceAll("(\\d+)-(\\d+)-(\\d+)", "$2/$3/$1");
    }
}
