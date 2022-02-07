package com.StudyingPlatform.controllers;

import com.StudyingPlatform.application.StudyingApplication;
import com.StudyingPlatform.model.Subject;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class StudentMyCoursesController {
    @FXML
    private Label courseLabel;
    Subject listedSubject;
    @FXML
    public void onQuitButtonClick() throws IOException {}
    @FXML
    public void onStudyingGroupsButtonClick() throws IOException {

    }
    @FXML
    public void onViewGradesButtonClick() throws IOException {
        URL url;
        Stage stage = StudyingApplication.getPrimaryStage();
        url = StudyingApplication.class.getResource("view-grades.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(url);
        Scene scene = new Scene(fxmlLoader.load(), 400, 500);
        stage.setScene(scene);
    }
    public void setSubject(Subject subject) {
        this.listedSubject = subject;
        courseLabel.setText(subject.getName());
    }
}
