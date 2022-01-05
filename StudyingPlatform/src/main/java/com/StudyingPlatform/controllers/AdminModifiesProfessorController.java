package com.StudyingPlatform.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AdminModifiesProfessorController extends AdminModifiesUserController{
    @FXML
    private TextField departmentField;

    @FXML
    public void onEditDepartmentClick(){
        disableAll();
        departmentField.setDisable(false);
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
        departmentField.setDisable(true);
    }
}
