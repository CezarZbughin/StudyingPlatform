package com.StudyingPlatform.model;

import java.sql.Date;

public class ProfessorSubject extends Subject {
    Professor professor;
    private int studentsCapacity;
    private float lectureWeight;
    private float seminarWeight;
    private float labWeight;
    private ScheduleTime scheduleLecture;
    private ScheduleTime scheduleSeminar;
    private ScheduleTime scheduleLab;

    public ProfessorSubject(){

    }

    public ProfessorSubject(int id, String name, String description, boolean hasLecture, boolean hasSeminar, boolean hasLab, Date dateStart, Date dateEnd, Professor professor, int studentsCapacity, float lectureWeight, float seminarWeight, float labWeight, ScheduleTime scheduleLecture, ScheduleTime scheduleSeminar, ScheduleTime scheduleLab) {
        super(id, name, description, hasLecture, hasSeminar, hasLab, dateStart, dateEnd);
        this.professor = professor;
        this.studentsCapacity = studentsCapacity;
        this.lectureWeight = lectureWeight;
        this.seminarWeight = seminarWeight;
        this.labWeight = labWeight;
        this.scheduleLecture = scheduleLecture;
        this.scheduleSeminar = scheduleSeminar;
        this.scheduleLab = scheduleLab;
    }
}
