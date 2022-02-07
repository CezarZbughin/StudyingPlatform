package com.StudyingPlatform.controllers;

import com.StudyingPlatform.application.StudyingApplication;
import com.StudyingPlatform.model.Student;
import com.StudyingPlatform.model.Subject;
import com.StudyingPlatform.model.SubjectStudent;
import com.StudyingPlatform.service.Exceptions.SubjectNotFoundException;
import com.StudyingPlatform.service.StudentService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class StudentSubjectsController implements Initializable {
    @FXML
    private TextField subjectNameField;
    @FXML
    private VBox subjectsVBox;
    @FXML
    private Button joinButton;

    private boolean showJoinable = false;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showPersonalCourses();
    }

    @FXML
    public void onSearchButtonClick() {

    }

    @FXML
    public void onBackButtonClick() {
        StudyingApplication.jumpToView("home.fxml");
    }

    @FXML
    public void onJoinButtonClick() {
        if(showJoinable){
            joinButton.setText("join Course");
            showPersonalCourses();
        }else{
            joinButton.setText("my Courses");
            showJoinableCourses();
        }
        showJoinable = !showJoinable;
    }

    private void showPersonalCourses() {
        subjectsVBox.getChildren().clear();
        try {
            List<SubjectStudent> subjects =
                    StudentService.studentGetSubjects((Student) SuperController.activeUser);
            addSubjects(subjects,false);
        } catch (SubjectNotFoundException e) {
            return;
        } catch(SQLException e){
            e.printStackTrace();
            return;
        }
    }

    private void showJoinableCourses() {
        subjectsVBox.getChildren().clear();
        try {
            List<Subject> subjects =
                    StudentService.studentGetJoinableSubjects((Student) SuperController.activeUser);
            addSubjects(subjects, true);
        }catch(SQLException e){
            e.printStackTrace();
            return;
        }
    }

    private void addSubjects(List<? extends Subject> subjects, boolean isJoinable){
        for(Subject subject:subjects){
            try {
                URL url = StudyingApplication.class.getResource("student-subjects-row.fxml");
                FXMLLoader fxmlLoader = new FXMLLoader(url);
                Parent row = (Parent) fxmlLoader.load();
                StudentSubjectsRowController controller = fxmlLoader.<StudentSubjectsRowController>getController();
                controller.set(subject,isJoinable);
                subjectsVBox.getChildren().add(row);
            }catch (IOException e){
                continue;
            }
        }
    }
}