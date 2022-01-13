package com.StudyingPlatform.controllers;

import com.StudyingPlatform.model.Student;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class AddGradesRowController {
    @FXML
    private Label nameLabel;
    Student listedStudent;

    @FXML
    public void onEditButtonClick(){

    }
    public void setStudent(Student student) {
        this.listedStudent = student;
        nameLabel.setText(student.getFirstName()+student.getLastName());
    }
}