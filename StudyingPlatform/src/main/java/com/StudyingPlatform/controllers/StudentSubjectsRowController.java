package com.StudyingPlatform.controllers;

import com.StudyingPlatform.application.StudyingApplication;
import com.StudyingPlatform.model.Subject;
import com.StudyingPlatform.model.SubjectProfessor;
import com.StudyingPlatform.model.SubjectStudent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class StudentSubjectsRowController {
    @FXML
    private Label subjectLabel;
    @FXML
    private Button firstButton;
    @FXML
    private Button secondButton;
    @FXML
    private MenuButton moreButton;

    private Subject subject;
    private boolean isJoinable;

    @FXML
    public void onFirstButtonClick(){
        if(isJoinable){
            //join
        }else{
            //activities
        }
    }
    @FXML
    public void onSecondButtonClick(){
        if(isJoinable){
            //grades
        }else{
            //view
        }
    }
    @FXML
    public void onViewButtonClick(){

    }
    @FXML
    public void onQuitButtonClick(){

    }

    public void set(Subject subject, boolean isJoinable){
        this.subject = subject;
        this.isJoinable = isJoinable;
        subjectLabel.setText(subject.getName());
        if(isJoinable){
            firstButton.setText("Join");
            secondButton.setText("View");
            moreButton.setVisible(false);
        }else{
            firstButton.setText("Activities");
            secondButton.setText("Grades");
            moreButton.setVisible(true);
        }
    }
}
