package com.StudyingPlatform.controllers;
import com.StudyingPlatform.application.StudyingApplication;
import com.StudyingPlatform.model.Address;
import com.StudyingPlatform.model.Student;
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
abstract class DisplayUserDataController implements Initializable{
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField phoneField;
    @FXML
    private TextField cnpField;
    @FXML
    private TextField ibanField;
    @FXML
    private TextField countryField;
    @FXML
    private TextField regionField;
    @FXML
    private TextField townField;
    @FXML
    private TextField streetAddressField;
    @FXML
    private TextField postalCodeField;
    @FXML
    private TextField contractNumberField;
    @FXML
    public void onBackButtonClick(){
        System.out.println("back");
    }
  /*  public void displayStudent(String firstNameField, String lastNameField,String emailField, String phoneField, String CNP,
                               String iban, Address address, String contractNumber, int year){
        this.firstNameField.setText(firstNameField);
        this.lastNameField.setText(lastNameField);
        this.emailField.setText(emailField);
        this.phoneField.setText(phoneField);
        this.cnpField.setText(CNP);
        this.ibanField.setText(iban);
        this.countryField.setText(address.getCountry());
        this.regionField.setText(address.getRegion());
        this.townField.setText(address.getTown());
        this.streetAddressField.setText(address.getStreetAddress());
        this.postalCodeField.setText(address.getPostalCode());
        this.contractNumberField.setText(contractNumber);
    }
      firstNameField.setText(SuperController.activeUser.getFirstName());
    */
    @FXML
    public void displayUser() {
        firstNameField.setText(SuperController.activeUser.getFirstName());
        lastNameField.setText(SuperController.activeUser.getLastName());
        emailField.setText(SuperController.activeUser.getEmail());
        phoneField.setText(SuperController.activeUser.getPhone());
        cnpField.setText(SuperController.activeUser.getCnp());
        ibanField.setText(SuperController.activeUser.getIban());
        countryField.setText(SuperController.activeUser.getAddress().getCountry());
        regionField.setText(SuperController.activeUser.getAddress().getRegion());
        townField.setText(SuperController.activeUser.getAddress().getTown());
        streetAddressField.setText(SuperController.activeUser.getAddress().getStreetAddress());
        postalCodeField.setText(SuperController.activeUser.getAddress().getPostalCode());
        contractNumberField.setText(SuperController.activeUser.getContractNumber());
    }
}
