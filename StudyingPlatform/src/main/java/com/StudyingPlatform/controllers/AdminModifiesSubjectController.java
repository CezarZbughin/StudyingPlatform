package com.StudyingPlatform.controllers;

import com.StudyingPlatform.application.StudyingApplication;
import com.StudyingPlatform.model.Student;
import com.StudyingPlatform.model.Subject;
import com.StudyingPlatform.model.User;
import com.StudyingPlatform.service.DataBaseService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.Calendar;

public class AdminModifiesSubjectController {
    @FXML
    TextField nameField;
    @FXML
    TextArea descriptionText;
    @FXML
    CheckBox lectureCheckBox;
    @FXML
    CheckBox seminarCheckBox;
    @FXML
    CheckBox labCheckBox;
    @FXML
    DatePicker startDatePicker;
    @FXML
    DatePicker finishDatePicker;

    private Subject displayedSubject;
    @FXML
    public void onEditNameFieldClick() {
        nameField.setDisable(false);
        descriptionText.setDisable(true);
    }
    @FXML
    public void onEditDescriptionTextClick() {
        nameField.setDisable(true);
        descriptionText.setDisable(false);
    }
    @FXML
    public void onSaveChangesButtonClick() throws IOException{
        this.displayedSubject.setName(nameField.getText());
        this.displayedSubject.setDescription(descriptionText.getText());
        try {
            DataBaseService.updateSubject(this.displayedSubject);
            SuperController.popMessage("Subject updated successfully");
        }catch(SQLException e) {
            SuperController.popError("Something went wrong.");
            e.printStackTrace();
        }

    }
    @FXML
    public void onBackButtonClick() throws IOException {
        Stage stage = StudyingApplication.getPrimaryStage();
        URL url = StudyingApplication.class.getResource("admin-view.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(url);
        Scene scene = new Scene(fxmlLoader.load(), 400, 500);
        stage.setScene(scene);
    }

    public void setSubject(Subject subject){
        this.displayedSubject = subject;
        updateView();
    }

    public Subject getDisplayedSubject() {
        return displayedSubject;
    }

    private void updateView(){
        nameField.setText(displayedSubject.getName());
        descriptionText.setText(displayedSubject.getDescription());
        lectureCheckBox.setSelected(displayedSubject.getHasLecture());
        seminarCheckBox.setSelected(displayedSubject.getHasSeminar());
        labCheckBox.setSelected(displayedSubject.getHasLab());
    }
}
