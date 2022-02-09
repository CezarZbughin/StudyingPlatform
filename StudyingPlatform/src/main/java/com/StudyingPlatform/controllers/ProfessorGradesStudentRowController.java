package com.StudyingPlatform.controllers;

import com.StudyingPlatform.model.Student;
import com.StudyingPlatform.model.SubjectStudent;
import com.StudyingPlatform.service.Exceptions.GradesNotFoundException;
import com.StudyingPlatform.service.StudentService;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class ProfessorGradesStudentRowController {
    @FXML
    private Label firstNameLabel;
    @FXML
    private Label lastNameLabel;
    @FXML
    private TextField lectureTextField;
    @FXML
    private TextField seminarTextField;
    @FXML
    private TextField labTextField;

    private Student student;
    private SubjectStudent subject;

    public void setStudent(Student student, SubjectStudent subject) {
        this.student = student;
        firstNameLabel.setText(student.getFirstName());
        lastNameLabel.setText(student.getLastName());
        //plus more
        int[] grades = null;
        try {
            grades = StudentService.studentGetGrades(student, subject);
        }catch (SQLException | GradesNotFoundException e){
            e.printStackTrace();
            return;
        }
        if (subject.getHasLecture()) {
            lectureTextField.setText(String.valueOf(grades[0]));
        } else {
            lectureTextField.setDisable(true);
        }
        if (subject.getHasSeminar()) {
            seminarTextField.setText(String.valueOf(grades[1]));
        } else {
            seminarTextField.setDisable(true);
        }
        if (subject.getHasLab()) {
            labTextField.setText(String.valueOf(grades[2]));
        }else{
            labTextField.setDisable(true);
        }
    }
}
