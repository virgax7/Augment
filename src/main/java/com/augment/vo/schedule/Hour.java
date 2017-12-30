package com.augment.vo.schedule;

public class Hour {
    private final String time;
    private final String task;

    public Hour(final String time, final String task) {
        this.time = time;
        this.task = task;
    }

    public String getTime() {
        return time;
    }

    public String getTask() {
        return task;
    }

    public enum TIME {
        FOUR_FIVE_AM("4:00 - 5:00 AM "), FIVE_SIX_AM("5:00 - 6:00 AM "), SIX_SEVEN_AM("6:00 - 7:00 AM "), SEVEN_EIGHT_AM("7:00 - 8:00 AM "),
        EIGHT_NINE_AM("8:00 - 9:00 AM "), NINE_TEN_AM("9:00 - 10:00 AM "), TEN_ELEVEN_AM("10:00 - 11:00 AM "), ELEVEN_TWELVE_PM("11:00 - 12:00 PM "),
        TWELVE_ONE_PM("12:00 - 1:00 PM "), ONE_TWO_PM("1:00 - 2:00 PM "), TWO_THREE_PM("2:00 - 3:00 PM "), THREE_FOUR_PM("3:00 - 4:00 PM "),
        FOUR_FIVE_PM("4:00 - 5:00 PM "), FIVE_SIX_PM("5:00 - 6:00 PM "), SIX_SEVEN_PM("6:00 - 7:00 PM "), SEVEN_EIGHT_PM("7:00 - 8:00 PM "),
        EIGHT_NINE_PM("8:00 - 9:00 PM "), NINE_TEN_PM("9:00 - 10:00 PM "), TEN_ELEVEN_PM("10:00 - 11:00 PM "), ELEVEN_TWELVE_AM("11:00 - 12:00 AM "),
        TWELVE_ONE_AM("12:00 - 1:00 AM "), ONE_TWO_AM("1:00 - 2:00 AM "), TWO_THREE_AM("2:00 - 3:00 AM "), THREE_FOUR_AM("3:00 - 4:00 AM ");

        private final String time;
        TIME(final String time) {
            this.time = time;
        }

        @Override
        public String toString() {
            return time;
        }
    }
}
