package com.StudyingPlatform.controllers;

import com.StudyingPlatform.application.StudyingApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class SingUpController {
    @FXML
    private VBox root;
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField CNP;

    @FXML
    private TextField country;
    @FXML
    private TextField town;
    @FXML
    private TextField street;
    @FXML
    private TextField postalCode;
    @FXML
    private TextField phone;
    @FXML
    private TextField email;
    @FXML
    private TextField iban;
    @FXML
    private TextField contractNumber;
    @FXML
    private TextField username;
    @FXML
    private Label error;
    @FXML
    private PasswordField password;
    @FXML
    private PasswordField confirmPassword;
    @FXML
    private Label error1;
    @FXML
    private VBox studentBox;
    @FXML
    private VBox professorBox;

    @FXML
    public void onStudentButtonClick() {

        professorBox.setVisible(false);
        studentBox.setVisible(true);
        studentBox.managedProperty().bind(studentBox.visibleProperty());
    }

    @FXML
    public void onProfessorButtonClick() {
        studentBox.setVisible(false);
        professorBox.setVisible(true);
        professorBox.managedProperty().bind(professorBox.visibleProperty());
    }

    @FXML
    public void onAlreadyHaveAnAccountLogInClick() throws IOException {
        Stage stage = StudyingApplication.getPrimaryStage();
        URL url = StudyingApplication.class.getResource("log-in-view.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(url);
        Scene scene = new Scene(fxmlLoader.load(), 400, 500);
        stage.setScene(scene);
    }

}
