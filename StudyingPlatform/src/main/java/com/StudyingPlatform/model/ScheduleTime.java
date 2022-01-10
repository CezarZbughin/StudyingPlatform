package com.StudyingPlatform.model;

import java.time.DayOfWeek;

public class ScheduleTime {
    DayOfWeek dayOfWeek;
    int hour;

    public ScheduleTime(){

    }

    public ScheduleTime(DayOfWeek dayOfWeek, int hour){
        this.dayOfWeek = dayOfWeek;
        this.hour = hour;
    }

    public ScheduleTime(String day, int hour){
        this.dayOfWeek = DayOfWeek.valueOf(day);
        this.hour = hour;
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
}
