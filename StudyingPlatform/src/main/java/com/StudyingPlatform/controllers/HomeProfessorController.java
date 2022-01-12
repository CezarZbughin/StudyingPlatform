package com.StudyingPlatform.controllers;

import com.StudyingPlatform.application.StudyingApplication;
import com.StudyingPlatform.model.Subject;
import com.StudyingPlatform.model.User;
import com.StudyingPlatform.service.DataBaseService;
import com.StudyingPlatform.service.Exceptions.SubjectNotFoundException;
import com.StudyingPlatform.service.Exceptions.UserNotFoundException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HomeProfessorController {
    @FXML
    private Button adminButton;
    public void makeAdminVisible(){
        if(SuperController.activeUser.isAdmin() || SuperController.activeUser.isSuperAdmin()){
            adminButton.setVisible(true);
        }
    }
    @FXML
    public void onTodayButtonClick() throws IOException {}
    @FXML
    public void onCatalogButtonClick() throws IOException {
        Stage stage = StudyingApplication.getPrimaryStage();
        URL url = StudyingApplication.class.getResource("Catalog.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(url);
        Scene scene = new Scene(fxmlLoader.load(), 400, 500);
        stage.setScene(scene);
    }
    @FXML
    public void onMyProfileButtonClick() throws IOException {
        Stage stage = StudyingApplication.getPrimaryStage();
        URL url = StudyingApplication.class.getResource("display-professor-data.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(url);
        Scene scene = new Scene(fxmlLoader.load(), 400, 500);
        stage.setScene(scene);
    }
   @FXML
    public void onMyCoursesButtonClick() throws IOException {
        Stage stage = StudyingApplication.getPrimaryStage();
        URL url = StudyingApplication.class.getResource("professor-subjects.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(url);
        Scene scene = new Scene(fxmlLoader.load(), 400, 500);
        stage.setScene(scene);
    }
    @FXML
    public void onAdminButtonClick() throws IOException {
        Stage stage = StudyingApplication.getPrimaryStage();
        URL url = StudyingApplication.class.getResource("Admin-view.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(url);
        Scene scene = new Scene(fxmlLoader.load(), 400, 500);
        stage.setScene(scene);
    }
    @FXML
    public void onLogOutButtonClick() throws IOException {

        Stage stage = StudyingApplication.getPrimaryStage();
        URL url = StudyingApplication.class.getResource("log-in-view.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(url);
        Scene scene = new Scene(fxmlLoader.load(), 400, 500);
        stage.setScene(scene);
    }

}
