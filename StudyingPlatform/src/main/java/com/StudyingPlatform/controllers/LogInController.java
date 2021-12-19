package com.StudyingPlatform.controllers;

import com.StudyingPlatform.service.AccountService;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.sql.Connection;

public class LogInController {
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label errorLabel;

    @FXML
    public void onLogInButtonClick(){
        AccountService.logIn(usernameField.getText(), passwordField.getText());
    }

    @FXML
    public void onCreateAccountClick(){

    }

}
