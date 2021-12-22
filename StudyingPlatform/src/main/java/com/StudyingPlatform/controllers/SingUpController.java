package com.StudyingPlatform.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class SingUpController {
    @FXML
    private VBox root;
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField CNP;

    @FXML
    private TextField country;
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
    private Label error;
    @FXML
    private PasswordField password;
    @FXML
    private PasswordField confirmPassword;
    @FXML
    private Label error1;
}
