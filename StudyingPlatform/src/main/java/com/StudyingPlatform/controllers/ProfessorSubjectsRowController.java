package com.StudyingPlatform.controllers;

import com.StudyingPlatform.application.StudyingApplication;
import com.StudyingPlatform.model.Subject;
import com.StudyingPlatform.model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class ProfessorSubjectsRowController {
    @FXML
    private Label nameLabel;

    private Subject displayedSubject;

    @FXML
    public void onViewButtonClick(){

    }
    @FXML
    public void onWeightsButtonClick(){

    }
    @FXML
    public void onScheduleButtonClick(){

    }

    public void setSubject(Subject subject){
        this.displayedSubject = subject;
        nameLabel.setText(subject.getName());
    }
}
