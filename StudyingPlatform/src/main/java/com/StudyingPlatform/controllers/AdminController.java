package com.StudyingPlatform.controllers;

import com.StudyingPlatform.application.StudyingApplication;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class AdminController {
    @FXML
    public void onUserListClick() {
        StudyingApplication.jumpToView("user-list-view.fxml");
    }

    @FXML
    public void onCourseListClick(){
        StudyingApplication.jumpToView("subject-list-view.fxml");
    }

    @FXML
    public void onAssignProfessorClick() {
        StudyingApplication.jumpToView("assign-professor-to-subject.fxml");
    }

    @FXML
    public void onBackButtonClick() {
        Stage stage = StudyingApplication.getPrimaryStage();
        if ("STUDENT".equals(SuperController.activeUser.getRole())) {
            StudyingApplication.jumpToView("home-student.fxml");
        } else if ("PROFESSOR".equals(SuperController.activeUser.getRole())) {
            StudyingApplication.jumpToView("home-professor.fxml");
        } else throw new IllegalStateException("Unexpected role for user");
    }
}