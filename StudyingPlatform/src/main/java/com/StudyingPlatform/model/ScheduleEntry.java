package com.StudyingPlatform.model;

public class ScheduleEntry {
    private ScheduleTime time;
    private String type;
    private String name;

    public ScheduleEntry(){

    }

    public ScheduleEntry(ScheduleTime time, String type, String name){
        this.time = time;
        this.type = type;
        this.name = name;
    }

    public ScheduleTime getTime() {
        return time;
    }

    public void setTime(ScheduleTime time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
