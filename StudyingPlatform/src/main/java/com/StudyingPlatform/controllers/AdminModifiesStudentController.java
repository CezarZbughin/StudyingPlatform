package com.StudyingPlatform.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AdminModifiesStudentController extends AdminModifiesUserController{
    @FXML
    private TextField yearField;

    @FXML
    public void onEditYearClick(){
        disableAll();
        yearField.setDisable(false);
    }

    @FXML
    public void onBackButtonClick(){
        System.out.println("back");
    }
    @FXML
    public void onSaveChangesButtonClick(){
        System.out.println("save changes");
    }

    void disableAll(){
         disableAllSuper();
         yearField.setDisable(true);
    }
}
