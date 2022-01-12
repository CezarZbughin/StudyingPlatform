package com.StudyingPlatform.controllers;

import com.StudyingPlatform.model.SubjectProfessor;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

public class StudentSearchCoursesController {
    @FXML
   private TextField courseLabel;

    private SubjectProfessor displayedSubject;
    @FXML
    public void onJoinButtonClick() throws IOException {}

   public void setSubject(SubjectProfessor subject){
        this.displayedSubject = subject;
        courseLabel.setText(subject.getName());
    }
}
