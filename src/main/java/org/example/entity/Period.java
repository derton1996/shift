package org.example.entity;

public class Period {

    private final String startDate;
    private final String targetDate;

    public Period(String startDate, String targetDate) {
        this.startDate = startDate;
        this.targetDate = targetDate;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getTargetDate() {
        return targetDate;
    }
}
