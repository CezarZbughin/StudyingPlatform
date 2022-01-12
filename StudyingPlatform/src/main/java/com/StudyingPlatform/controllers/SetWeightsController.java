package com.StudyingPlatform.controllers;

import com.StudyingPlatform.model.Subject;
import com.StudyingPlatform.service.DataBaseService;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.sql.Date;

public class SetWeightsController {


    private Stage myStage;
    @FXML
    private TextField lectureText;
    @FXML
    private TextField seminarText;
    @FXML
    private TextField laboratourText;
   @FXML
  private Label messageText;

    @FXML
    public void onSetButtonClick(){
        messageText.setVisible(true);
        if(lectureText.getText().equals("") || seminarText.getText().equals("")||
                laboratourText.getText().equals("")) {
            messageText.setVisible(false);
        }
        else{
            if(Integer.parseInt(lectureText.getText())+Integer.parseInt(seminarText.getText())+
                    Integer.parseInt(laboratourText.getText())==100){
            }
        }


    }
    @FXML
    public void onCloseButtonClick(){
        myStage.close();
    }

    public void create( Stage stage){
        myStage = stage;

    }
}
