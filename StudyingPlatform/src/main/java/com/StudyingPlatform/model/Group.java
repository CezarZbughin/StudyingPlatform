package com.StudyingPlatform.model;

public class Group {
    private Integer id;
    private Integer subjectId;
    private String nume;

    public Group(Integer id, String nume) {
        this.id=id;
        this.nume=nume;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }
}
