package com.StudyingPlatform.controllers;
import com.StudyingPlatform.application.StudyingApplication;
import com.StudyingPlatform.model.Address;
import com.StudyingPlatform.model.Professor;
import com.StudyingPlatform.model.User;
import com.StudyingPlatform.service.DataBaseService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
public class DisplayProfessorDataController {
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField email;
    @FXML
    private TextField phone;
    @FXML
    private TextField CNP;
    @FXML
    private TextField iban;
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
    private TextField contractNumber;
    @FXML
    private TextField department;
    @FXML
    public void displayProfessor(String firstName, String lastName,String email, String phone, String CNP,
                               String iban, Address address, String contractNumber, String department){
        this.firstName.setText(firstName);
        this.lastName.setText(lastName);
        this.email.setText(email);
        this.phone.setText(phone);
        this.CNP.setText(CNP);
        this.iban.setText(iban);
        this.country.setText(address.getCountry());
        this.region.setText(address.getRegion());
        this.town.setText(address.getTown());
        this.street.setText(address.getStreetAddress());
        this.postalCode.setText(address.getPostalCode());
        this.contractNumber.setText(contractNumber);
        this.department.setText(department);

    }
}
