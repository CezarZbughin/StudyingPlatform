package com.StudyingPlatform.controllers;

import com.StudyingPlatform.application.StudyingApplication;
import com.StudyingPlatform.model.SubjectProfessor;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class StudentActivitiesController {
    @FXML
    private Label lectureDay;
    @FXML
    private Label lectureStarts;
    @FXML
    private Label lectureEnds;
    @FXML
    private Button lectureJoin;
    @FXML
    private Label seminarDay;
    @FXML
    private Label seminarStarts;
    @FXML
    private Label seminarEnds;
    @FXML
    private Button seminarJoin;
    @FXML
    private Label laboratourDay;
    @FXML
    private Label laboratourStarts;
    @FXML
    private Label laboratourEnds;
    @FXML
    private Button laboratourJoin;

    public void set(SubjectProfessor subject) {
        if (subject.getHasLecture()) {
            lectureDay.setText(subject.getScheduleLecture().getDayOfWeek().toString());
            lectureStarts.setText(String.valueOf(subject.getScheduleLecture().getHour()));
            lectureEnds.setText(String.valueOf(subject.getScheduleLecture().getHour() + subject.getScheduleLecture().getDuration()));
        } else {
            lectureDay.setVisible(false);
            lectureStarts.setVisible(false);
            lectureEnds.setVisible(false);
        }
        if (subject.getHasSeminar()) {
            seminarDay.setText(subject.getScheduleLecture().getDayOfWeek().toString());
            seminarStarts.setText(String.valueOf(subject.getScheduleLecture().getHour()));
            seminarEnds.setText(String.valueOf(subject.getScheduleLecture().getHour() + subject.getScheduleLecture().getDuration()));
        } else {
            seminarDay.setVisible(false);
            seminarStarts.setVisible(false);
            seminarEnds.setVisible(false);
        }
        if (subject.getHasLab()) {
            laboratourDay.setText(subject.getScheduleLecture().getDayOfWeek().toString());
            laboratourStarts.setText(String.valueOf(subject.getScheduleLecture().getHour()));
            laboratourEnds.setText(String.valueOf(subject.getScheduleLecture().getHour() + subject.getScheduleLecture().getDuration()));
        } else {
            laboratourDay.setVisible(false);
            laboratourStarts.setVisible(false);
            laboratourEnds.setVisible(false);
        }
    }

    @FXML
    public void onLectureJoinButton() {

    }

    @FXML
    public void onSeminarJoinButton() {

    }

    @FXML
    public void onLaboratourJoinButton() {

    }

    @FXML
    public void onBackButton() {
        StudyingApplication.jumpToView("student-subjects.fxml");
    }


}
