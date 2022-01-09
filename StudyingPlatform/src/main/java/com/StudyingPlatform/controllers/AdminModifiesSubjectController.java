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

public class AdminModifiesSubjectController {
    private Subject displayedSubject;
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

    public void setSubject(Subject subject){
        this.displayedSubject=subject;
        updateDisplayedUser();
    }
    void disableAll() {
        nameField.setDisable(true);
        descriptionText.setDisable(true);
        lectureCheckBox.setDisable(true);
        seminarCheckBox.setDisable(true);
        labCheckBox.setDisable(true);
        startDatePicker.setDisable(true);
        finishDatePicker.setDisable(true);
    }

    public Subject getDisplayedSubject() {
        return displayedSubject;
    }

    public void updateDisplayedUser() {
        displayedSubject.setName(nameField.getText());
        displayedSubject.setDescription(descriptionText.getText());
        displayedSubject.setHasLecture(lectureCheckBox.isSelected());
        displayedSubject.setHasSeminar(seminarCheckBox.isSelected());
        displayedSubject.setHasLab(labCheckBox.isSelected());
        displayedSubject.setDateStart(Date.valueOf(startDatePicker.getValue()));
        displayedSubject.setDateEnd(Date.valueOf(finishDatePicker.getValue()));

    }


    @FXML
    public void onEditNameFieldClick() {
        disableAll();
        nameField.setDisable(false);
    }

    @FXML
    public void onEditDescriptionTextClick() {
        disableAll();
        descriptionText.setDisable(false);
    }

    @FXML
    public void onEditLectureCheckBoxClick() {
        disableAll();
        lectureCheckBox.setDisable(false);
    }

    @FXML
    public void onEditSeminarCheckBoxClick() {
        disableAll();
        seminarCheckBox.setDisable(false);
    }

    @FXML
    public void onEditLabCheckBoxClick() {
        disableAll();
        labCheckBox.setDisable(false);
    }

    @FXML
    public void onEditStartDatePickerClick() {
        disableAll();
        startDatePicker.setDisable(false);
    }

    @FXML
    public void onEditFinishDatePickerClick() {
        disableAll();
        finishDatePicker.setDisable(false);
    }

    @FXML
    public void onSaveChangesButtonClick() {
        updateDisplayedUser();
        try {
            DataBaseService.updateSubject(getDisplayedSubject());
        } catch (SQLException e) {
            System.out.println("something went worng");
            return;
        }
    }

    @FXML
    public void onBackButtonClick() throws IOException {
        URL url;
        Stage stage = StudyingApplication.getPrimaryStage();
        if (SuperController.activeUser.equals("STUDENT")) {
            url = StudyingApplication.class.getResource("home-student.fxml");
        } else {
            url = StudyingApplication.class.getResource("home-professor.fxml");
        }
        FXMLLoader fxmlLoader = new FXMLLoader(url);
        Scene scene = new Scene(fxmlLoader.load(), 400, 500);
        stage.setScene(scene);

    }
}
