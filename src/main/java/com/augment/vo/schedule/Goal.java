package com.augment.vo.schedule;

import lombok.Getter;

@Getter
public class Goal {
    private final String title;
    private final String description;
    private final String startDate;
    private final String targetDate;
    private final String status;
    private final boolean archived;

    public Goal(final String title, final String description, final String startDate, final String targetDate, final String status, final boolean archived) {
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.targetDate = targetDate;
        this.status = status;
        this.archived = archived;
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
