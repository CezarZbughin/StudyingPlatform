package com.StudyingPlatform.controllers;

import com.StudyingPlatform.application.StudyingApplication;
import com.StudyingPlatform.model.*;
import com.StudyingPlatform.service.DataBaseService;
import com.StudyingPlatform.service.Exceptions.SubjectNotFoundException;
import com.StudyingPlatform.service.Exceptions.UserNotFoundException;
import com.StudyingPlatform.service.ProfessorService;
import com.StudyingPlatform.service.StudentService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class StudentCoursesController implements Initializable {

    @FXML
    VBox studentsVBox;
    @FXML
    VBox studentsVBox1;
    @FXML
    TextField courseNameField;

    private List<SubjectStudent> listedSubjects;
    private List<Subject> subjects;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            listedSubjects = StudentService.studentGetSubjects((Student) SuperController.activeUser);
        } catch (SQLException e) {
            e.printStackTrace();
            listedSubjects = new ArrayList<>();
        } catch (SubjectNotFoundException e) {
            listedSubjects = new ArrayList<>();
        }
        try{
            updateList();
        }catch (IOException e){
            e.printStackTrace();
            return;
        }
    }

    private void updateList() throws IOException {
        studentsVBox.getChildren().clear();
        for (SubjectStudent subject : listedSubjects) {
            URL url = StudyingApplication.class.getResource("student-my-courses-row.fxml");
            FXMLLoader fxmlLoader = new FXMLLoader(url);
            Parent row = (Parent) fxmlLoader.load();
            StudentMyCoursesController controller = fxmlLoader.<StudentMyCoursesController>getController();
            controller.setSubject(subject);
            studentsVBox.getChildren().add(row);
        }
    }
    private void updateList1() throws IOException {
        studentsVBox1.getChildren().clear();
            URL url = StudyingApplication.class.getResource("student-my-courses-row.fxml");
            FXMLLoader fxmlLoader = new FXMLLoader(url);
            Parent row = (Parent) fxmlLoader.load();
            StudentSearchCoursesController controller = fxmlLoader.<StudentSearchCoursesController>getController();
            controller.setSubject((SubjectProfessor) subjects);
            studentsVBox.getChildren().add(row);

    }
    @FXML
    public void onSearchButtonClick() throws IOException{

        try {
            subjects =  DataBaseService.getSubjectsByName((courseNameField.getText()));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (SubjectNotFoundException e) {
            //show error
            subjects=  new ArrayList<>();
        }
        updateList1();
    }
    @FXML
    public void onBackButtonClick() throws IOException {
        URL url;
        Stage stage = StudyingApplication.getPrimaryStage();
        url = StudyingApplication.class.getResource("home-student.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(url);
        Scene scene = new Scene(fxmlLoader.load(), 400, 500);
        stage.setScene(scene);
    }
}