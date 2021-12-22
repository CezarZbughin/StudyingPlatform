package com.StudyingPlatform.controllers;

import com.StudyingPlatform.application.StudyingApplication;
import com.StudyingPlatform.service.AccountService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.security.auth.login.LoginException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;

public class LogInController {
    @FXML
    private VBox root;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label errorLabel;

    @FXML
    public void onLogInButtonClick() throws IOException {
        Stage stage = (Stage) root.getScene().getWindow();
        try {
            AccountService.logIn(usernameField.getText(), passwordField.getText());
        }catch(LoginException e){
            switch(e.getMessage()){
                case "username not found":
                    errorLabel.setText("The username that you've entered doesn't belong to an account.");
                    break;
                case "wrong password":
                    errorLabel.setText("Your password was incorrect. Try again");
                    break;
                default:
                    errorLabel.setText("Sorry, something went wrong.");
            }
            errorLabel.setVisible(true);
            return;
        }
        URL url = StudyingApplication.class.getResource("green.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(url);
        Scene scene = new Scene(fxmlLoader.load(), 400, 500);
        stage.setScene(scene);
    }

    @FXML
    public void onCreateAccountClick(){}

}
