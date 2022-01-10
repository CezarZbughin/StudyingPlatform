package com.StudyingPlatform.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class SetWeightsController {


    private Stage myStage;
/*    @FXML
   private TextField messageText;
    @FXML
    public void onSetButtonClick(){
        messageText.setVisible(true);

    }
*/    @FXML
    public void onCloseButtonClick(){
        myStage.close();
    }

    public void create( Stage stage){
        myStage = stage;

    }
}
