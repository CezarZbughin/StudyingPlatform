package com.StudyingPlatform.controllers;
import com.StudyingPlatform.application.StudyingApplication;
import com.StudyingPlatform.model.Address;
import com.StudyingPlatform.model.Professor;
import com.StudyingPlatform.model.User;
import com.StudyingPlatform.service.DataBaseService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
public class DisplayProfessorDataController extends DisplayUserDataController{

    @FXML
    private TextField departmentField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        displayUser();
        departmentField.setText(((Professor) SuperController.activeUser).getDepartment());
    }
    @FXML
    public void onBackButtonClick() throws IOException {
        Stage stage = StudyingApplication.getPrimaryStage();
        URL url = StudyingApplication.class.getResource("home-professor.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(url);
        Scene scene = new Scene(fxmlLoader.load(), 400, 500);
        stage.setScene(scene);
    }
}
