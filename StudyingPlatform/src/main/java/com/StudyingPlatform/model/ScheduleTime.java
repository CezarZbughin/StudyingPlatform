package com.StudyingPlatform.model;

import java.time.DayOfWeek;

public class ScheduleTime {
    DayOfWeek dayOfWeek;
    int hour;
    int duration;

    public ScheduleTime() {

    }

    public ScheduleTime(DayOfWeek dayOfWeek, int hour, int duration) {
        this.dayOfWeek = dayOfWeek;
        this.hour = hour;
        this.duration = duration;
    }

    public ScheduleTime(String day, int hour, int duration) {
        if (day != null) {
            this.dayOfWeek = DayOfWeek.valueOf(day);
        } else {
            this.dayOfWeek = null;
        }
        this.hour = hour;
        this.duration = duration;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
