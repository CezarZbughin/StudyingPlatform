package com.StudyingPlatform.controllers;

import com.StudyingPlatform.model.Professor;
import com.StudyingPlatform.service.DataBaseService;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class AdminModifiesProfessorController extends AdminModifiesUserController{
    @FXML
    private TextField departmentField;

    @FXML
    public void onEditDepartmentClick(){
        disableAll();
        departmentField.setDisable(false);
    }
    @FXML
    public void onSaveChangesButtonClick(){
        updateDisplayedUser();
        ((Professor)getDisplayedUser()).setDepartment(departmentField.getText());
        try{
            DataBaseService.updateUser(getDisplayedUser());
        }catch(SQLException e){
            System.out.println("something went worng");
            return;
        }
    }
    void disableAll(){
        disableAllSuper();
        departmentField.setDisable(true);
    }
    public void updateView(){
        updateSuper();
        departmentField.setText(((Professor)getDisplayedUser()).getDepartment());
    }
}
