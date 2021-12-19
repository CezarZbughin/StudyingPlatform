package com.StudyingPlatform.model;

public class Teaching {
    private int subjectId;
    private int professorId;
    private int studentsCapacity;
    private float lectureWeight;
    private float seminarWeight;
    private float labWeight;
    private String scheduleLectureDay;
    private String scheduleSeminarDay;
    private String scheduleLabDay;
    private int scheduleLectureHour;
    private int scheduleSeminarHour;
    private int scheduleLabHour;

    public Teaching(){

    }

    public Teaching(int subjectId, int professorId, int studentsCapacity, float lectureWeight,
                    float seminarWeight, float labWeight, String scheduleLectureDay,
                    String scheduleSeminarDay, String scheduleLabDay, int scheduleLectureHour,
                    int scheduleSeminarHour, int scheduleLabHour) {
        this.subjectId = subjectId;
        this.professorId = professorId;
        this.studentsCapacity = studentsCapacity;
        this.lectureWeight = lectureWeight;
        this.seminarWeight = seminarWeight;
        this.labWeight = labWeight;
        this.scheduleLectureDay = scheduleLectureDay;
        this.scheduleSeminarDay = scheduleSeminarDay;
        this.scheduleLabDay = scheduleLabDay;
        this.scheduleLectureHour = scheduleLectureHour;
        this.scheduleSeminarHour = scheduleSeminarHour;
        this.scheduleLabHour = scheduleLabHour;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public int getProfessorId() {
        return professorId;
    }

    public void setProfessorId(int professorId) {
        this.professorId = professorId;
    }

    public int getStudentsCapacity() {
        return studentsCapacity;
    }

    public void setStudentsCapacity(int studentsCapacity) {
        this.studentsCapacity = studentsCapacity;
    }

    public float getLectureWeight() {
        return lectureWeight;
    }

    public void setLectureWeight(float lectureWeight) {
        this.lectureWeight = lectureWeight;
    }

    public float getSeminarWeight() {
        return seminarWeight;
    }

    public void setSeminarWeight(float seminarWeight) {
        this.seminarWeight = seminarWeight;
    }

    public float getLabWeight() {
        return labWeight;
    }

    public void setLabWeight(float labWeight) {
        this.labWeight = labWeight;
    }

    public String getScheduleLectureDay() {
        return scheduleLectureDay;
    }

    public void setScheduleLectureDay(String scheduleLectureDay) {
        this.scheduleLectureDay = scheduleLectureDay;
    }

    public String getScheduleSeminarDay() {
        return scheduleSeminarDay;
    }

    public void setScheduleSeminarDay(String scheduleSeminarDay) {
        this.scheduleSeminarDay = scheduleSeminarDay;
    }

    public String getScheduleLabDay() {
        return scheduleLabDay;
    }

    public void setScheduleLabDay(String scheduleLabDay) {
        this.scheduleLabDay = scheduleLabDay;
    }

    public int getScheduleLectureHour() {
        return scheduleLectureHour;
    }

    public void setScheduleLectureHour(int scheduleLectureHour) {
        this.scheduleLectureHour = scheduleLectureHour;
    }

    public int getScheduleSeminarHour() {
        return scheduleSeminarHour;
    }

    public void setScheduleSeminarHour(int scheduleSeminarHour) {
        this.scheduleSeminarHour = scheduleSeminarHour;
    }

    public int getScheduleLabHour() {
        return scheduleLabHour;
    }

    public void setScheduleLabHour(int scheduleLabHour) {
        this.scheduleLabHour = scheduleLabHour;
    }
}
