package com.StudyingPlatform.controllers;

import com.StudyingPlatform.application.StudyingApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class JoinSubjectController implements Initializable {
    @FXML
    VBox coursesVBox;
    @FXML
    Button confirmButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    public void onConfirmButtonClick() throws IOException {
        URL url = StudyingApplication.class.getResource("join-subject-row.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(url);
        coursesVBox.getChildren().add(fxmlLoader.load());
    }
}
