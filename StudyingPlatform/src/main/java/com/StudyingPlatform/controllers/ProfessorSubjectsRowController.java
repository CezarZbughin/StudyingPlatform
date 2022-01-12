package com.StudyingPlatform.controllers;

import com.StudyingPlatform.application.StudyingApplication;
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

public class ProfessorSubjectsRowController {
    @FXML
    private Label nameLabel;

    private SubjectProfessor displayedSubject;

    @FXML
    public void onViewButtonClick(){

    }
    @FXML
    public void onWeightsButtonClick() throws IOException {
        Stage stage = new Stage();
        URL url = StudyingApplication.class.getResource("set-weights.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(url);
        Parent root = (Parent) fxmlLoader.load();
        SetWeightsController controller = fxmlLoader.<SetWeightsController>getController();
        controller.create( stage);
        Scene scene = new Scene(root, 300, 90);
         stage.setScene(scene);
          stage.show();

    }
    @FXML
    public void onScheduleButtonClick() throws IOException{
        Stage stage = new Stage();
        URL url = StudyingApplication.class.getResource("schedule-activities.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(url);
        Parent root = (Parent) fxmlLoader.load();
        ScheduleActivitiesController controller = fxmlLoader.<ScheduleActivitiesController>getController();
        controller.create(displayedSubject,stage);
        Scene scene = new Scene(root, 300, 165);
        stage.setTitle("Schedule Activities");
        stage.setScene(scene);
        stage.show();
    }

    public void setSubject(SubjectProfessor subject){
        this.displayedSubject = subject;
        nameLabel.setText(subject.getName());
    }

}
