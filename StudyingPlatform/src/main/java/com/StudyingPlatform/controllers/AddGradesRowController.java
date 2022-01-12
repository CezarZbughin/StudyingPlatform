package com.StudyingPlatform.controllers;

import com.StudyingPlatform.application.StudyingApplication;
import com.StudyingPlatform.model.Student;
import com.StudyingPlatform.model.Subject;
import com.StudyingPlatform.model.SubjectProfessor;
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

public class AddGradesRowController {
    @FXML
    private Label nameLabel;
    Student listedStudent;

    @FXML
    public void onEditButtonClick() throws IOException {

    }
    public void setStudent(Student student) {
        this.listedStudent = student;
        nameLabel.setText(student.getFirstName()+student.getLastName());
    }
}