package com.augment.vo.schedule;

public class Goal {
    private final String title;
    private final String description;
    private final String startDate;
    private final String targetDate;

    public Goal(final String title, final String description, final String startDate, final String targetDate) {
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.targetDate = targetDate;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getTargetDate() {
        return targetDate;
    }

    public enum STATUS {
        NOT_IN_PROGRESS("not_in_progress"), IN_PROGRESS("in_progress"), ACCOMPLISHED("accomplished");

        private final String status;
        STATUS(final String status) {
            this.status = status;
        }

        @Override
        public String toString() {
            return status;
        }
    }
}
