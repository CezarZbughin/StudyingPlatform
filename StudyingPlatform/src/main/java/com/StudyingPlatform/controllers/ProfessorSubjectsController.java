package com.StudyingPlatform.controllers;

import com.StudyingPlatform.application.StudyingApplication;
import com.StudyingPlatform.model.Professor;
import com.StudyingPlatform.model.Subject;
import com.StudyingPlatform.service.DataBaseService;
import com.StudyingPlatform.service.Exceptions.SubjectNotFoundException;
import com.StudyingPlatform.service.ProfessorService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ProfessorSubjectsController implements Initializable {
    @FXML
    VBox subjectsVBox;

    private List<Subject> listedSubjects;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            listedSubjects = ProfessorService.professorGetSubjects((Professor) SuperController.activeUser);
        } catch (SQLException e) {
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
        subjectsVBox.getChildren().clear();
        for (Subject subject : listedSubjects) {
            URL url = StudyingApplication.class.getResource("professor-subjects-row.fxml");
            FXMLLoader fxmlLoader = new FXMLLoader(url);
            Parent row = (Parent) fxmlLoader.load();
            ProfessorSubjectsRowController controller = fxmlLoader.<ProfessorSubjectsRowController>getController();
            controller.setSubject(subject);
            subjectsVBox.getChildren().add(row);
        }
    }

    @FXML
    public void onBackButtonClick() throws IOException {
        URL url;
        Stage stage = StudyingApplication.getPrimaryStage();
        url = StudyingApplication.class.getResource("home-professor.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(url);
        Scene scene = new Scene(fxmlLoader.load(), 400, 500);
        stage.setScene(scene);

    }




}
