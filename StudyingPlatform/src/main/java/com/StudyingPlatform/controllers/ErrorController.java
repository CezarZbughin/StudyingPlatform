package com.StudyingPlatform.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ErrorController {
    @FXML
    private Label errorLabel;

    private Stage myStage;

    @FXML
    public void onCloseButtonClick(){
        myStage.close();
    }

    public void create(String message, Stage stage){
        myStage = stage;
        errorLabel.setText(message);
    }
}
