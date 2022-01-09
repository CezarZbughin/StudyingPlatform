package com.StudyingPlatform.controllers;

import com.StudyingPlatform.application.StudyingApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class AdminController {
    @FXML
    public void onUserListClick() throws IOException {
        Stage stage = StudyingApplication.getPrimaryStage();
        URL url = StudyingApplication.class.getResource("user-list-view.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(url);
        Scene scene = new Scene(fxmlLoader.load(), 400, 500);
        stage.setScene(scene);
    }
    @FXML
    public void onCourseListClick() throws IOException {
        Stage stage = StudyingApplication.getPrimaryStage();
        URL url = StudyingApplication.class.getResource("subject-list-view.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(url);
        Scene scene = new Scene(fxmlLoader.load(), 400, 500);
        stage.setScene(scene);

    }
    @FXML
    public void onAssignProfessorClick() throws IOException {
        Stage stage = StudyingApplication.getPrimaryStage();
        URL url = StudyingApplication.class.getResource("assign-professor-to-subject.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(url);
        Scene scene = new Scene(fxmlLoader.load(), 400, 500);
        stage.setScene(scene);
    }
    @FXML
    public void onBackClick() throws IOException {
        URL url;
        Stage stage = StudyingApplication.getPrimaryStage();
        if(SuperController.activeUser.equals("STUDENT")) {
            url = StudyingApplication.class.getResource("home-student.fxml");
        }else
        {
             url = StudyingApplication.class.getResource("home-professor.fxml");
        }
        FXMLLoader fxmlLoader = new FXMLLoader(url);
        Scene scene = new Scene(fxmlLoader.load(), 400, 500);
        stage.setScene(scene);

    }
}