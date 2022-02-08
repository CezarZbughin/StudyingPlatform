package com.StudyingPlatform.controllers;


import com.StudyingPlatform.application.StudyingApplication;
import com.StudyingPlatform.model.Student;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AddGradesController implements Initializable {
    @FXML
    private VBox subjectsVBox;

    private List<Student> listedStudents;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    private void updateList(){

    }

    @FXML
    public void onBackButtonClick() throws IOException {
        StudyingApplication.jumpToView("professor-grades.fxml");
    }
    @FXML
    public void onDownloadButtonClick() throws IOException {

    }

    @FXML
    public void onSaveChangesButtonClick() throws IOException {

    }
}