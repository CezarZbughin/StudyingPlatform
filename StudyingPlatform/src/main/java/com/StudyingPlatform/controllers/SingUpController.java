package com.StudyingPlatform.controllers;

import com.StudyingPlatform.application.StudyingApplication;
import com.StudyingPlatform.model.User;
import com.StudyingPlatform.service.DataBaseService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class SingUpController {
    ObservableList<Integer> yearOfStudyList = FXCollections.observableArrayList(1, 2, 3, 4);
    ObservableList<String> departamentList = FXCollections.observableArrayList("math", "computer sciene", "data base");
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField CNP;

    @FXML
    private TextField country;
    @FXML
    private TextField region;
    @FXML
    private TextField town;
    @FXML
    private TextField street;
    @FXML
    private TextField postalCode;
    @FXML
    private TextField phone;
    @FXML
    private TextField email;
    @FXML
    private TextField iban;
    @FXML
    private TextField contractNumber;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private PasswordField confirmPassword;
    @FXML
    private RadioButton studentButton;
    @FXML
    private RadioButton professorButton;
    @FXML
    private ChoiceBox yearOfStudy;
    @FXML
    private ChoiceBox departament;

    @FXML
    private VBox studentBox;
    @FXML
    private VBox professorBox;
    @FXML
    private Button continueButton;
    @FXML
    private Label incompleteFields;
    @FXML
    private Label minStudyingHours;
    @FXML
    private Label maxStudyingHours;
    @FXML
    private Label minTeachingHours;
    @FXML
    private Label maxTeachingHours;

    @FXML
    private void initialize() {
        yearOfStudy.setItems(yearOfStudyList);
        departament.setItems(departamentList);
    }


    @FXML
    public void onStudentButtonClick() {

        professorBox.setVisible(false);
        studentBox.setVisible(true);
        studentBox.managedProperty().bind(studentBox.visibleProperty());
    }

    @FXML
    public void onProfessorButtonClick() {
        studentBox.setVisible(false);
        professorBox.setVisible(true);
        professorBox.managedProperty().bind(professorBox.visibleProperty());
    }

    @FXML
    public void onAlreadyHaveAnAccountLogInClick() throws IOException {
        Stage stage = StudyingApplication.getPrimaryStage();
        URL url = StudyingApplication.class.getResource("log-in-view.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(url);
        Scene scene = new Scene(fxmlLoader.load(), 400, 500);
        stage.setScene(scene);
    }

    @FXML
    public Integer validation() {
        incompleteFields.setText("");
        if (firstName.getText().trim().isEmpty()) {
            incompleteFields.setText("First name field can not be empty");
            return 0;

        }
        if (lastName.getText().trim().isEmpty()) {
            incompleteFields.setText("Last name field can not be empty");

            return 0;
        }
        if (CNP.getText().trim().isEmpty()) {
            incompleteFields.setText("CNP field can not be empty");
            return 0;
        }
        if (country.getText().trim().isEmpty()) {
            incompleteFields.setText("Country can not be empty");
            return 0;
        }
        if (region.getText().trim().isEmpty()) {
            incompleteFields.setText("Region field can not be empty");
            return 0;
        }
        if (town.getText().trim().isEmpty()) {
            incompleteFields.setText("Town field can not be empty");
            return 0;
        }
        if (street.getText().trim().isEmpty()) {
            incompleteFields.setText("Street field can not be empty");
            return 0;
        }
        if (postalCode.getText().trim().isEmpty()) {
            incompleteFields.setText("Postal code field can not be empty");
            return 0;
        }
        if (phone.getText().trim().isEmpty()) {
            incompleteFields.setText("phone field can not be empty");
            return 0;
        }
        if (email.getText().trim().isEmpty()) {
            incompleteFields.setText("email field can not be empty");
            return 0;
        }
        if (iban.getText().trim().isEmpty()) {
            incompleteFields.setText("iban field can not be empty");
            return 0;
        }
        if (contractNumber.getText().trim().isEmpty()) {
            incompleteFields.setText("Contract number field can not be empty");
            return 0;
        }
        if (username.getText().trim().isEmpty()) {
            incompleteFields.setText("Username field can not be empty");
            return 0;
        } else {
            String usernameString = username.getText();
            User user = DataBaseService.getUserByUsername(usernameString);
            if (user != null) {
                incompleteFields.setText("Username field can not be empty");
                return 0;
            }
        }
        if (password.getText().trim().isEmpty()) {
            incompleteFields.setText("password field can not be empty");
            return 0;
        }
        if (!password.getText().equals(confirmPassword.getText())) {
            incompleteFields.setText("password doesn't match");
            return 0;
        }
        if (!studentButton.isSelected() && !professorButton.isSelected()) {
            incompleteFields.setText("Select account type");
            return 0;

        }
        if (studentButton.isSelected()) {
            if (yearOfStudy.getSelectionModel().isEmpty()) {
                incompleteFields.setText("Choose year of study");
                return 0;
            }
        }
        if (professorButton.isSelected()) {
            if (departament.getSelectionModel().isEmpty()) {
                incompleteFields.setText("Choose department");
                return 0;
            }
        }


        return 1;


    }

    @FXML
    public void onContinueButtonClick() throws IOException {
        Integer v = validation();
        if (v == 1) {
            Stage stage = StudyingApplication.getPrimaryStage();
            URL url = StudyingApplication.class.getResource("log-in-view.fxml");
            FXMLLoader fxmlLoader = new FXMLLoader(url);
            Scene scene = new Scene(fxmlLoader.load(), 400, 500);
            stage.setScene(scene);
        }
    }
}


