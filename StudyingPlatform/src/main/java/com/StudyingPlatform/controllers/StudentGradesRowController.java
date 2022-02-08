package com.StudyingPlatform.controllers;

import com.StudyingPlatform.model.Student;
import com.StudyingPlatform.model.Subject;
import com.StudyingPlatform.service.Exceptions.GradesNotFoundException;
import com.StudyingPlatform.service.StudentService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.stage.Stage;

import java.sql.SQLException;

public class StudentGradesRowController {
    @FXML
    private Label courseNameLabel;
    @FXML
    private Label lectureGrade;
    @FXML
    private Label seminarGrade;
    @FXML
    private Label labGrade;

    public void set(Student student, Subject subject, Stage myStage)  {
       courseNameLabel.setText(subject.getName());
        int [] grades= new int[3];
        try {
            grades = StudentService.studentGetGrades(student, subject);

        } catch (GradesNotFoundException | SQLException e) {
            myStage.close();
            SuperController.popError("Something went wrong");
            return;
        }
        if(subject.getHasLecture()){
            if(grades[0] != 0)
                lectureGrade.setText(String.valueOf(grades[0]));
            else
                lectureGrade.setText("NECULES");
        }
        if(subject.getHasSeminar()){
            if(grades[1] != 0)
                seminarGrade.setText(String.valueOf(grades[1]));
            else
                seminarGrade.setText("NECULES");
        }
        if(subject.getHasLecture()){
            if(grades[2] != 0)
                labGrade.setText(String.valueOf(grades[2]));
            else
                labGrade.setText("NECULES");
        }

    }
}
