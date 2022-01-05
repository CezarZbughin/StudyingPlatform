package com.StudyingPlatform.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

abstract class AdminModifiesUserController {
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
    private Label title;

    @FXML
    public void onEditFirstNameClick(){
        disableAll();
        firstNameField.setDisable(false);
    }
    @FXML
    public void onEditLastNameClick(){
        disableAll();
        lastNameField.setDisable(false);
    }
    @FXML
    public void onEditEmailClick(){
        disableAll();
        emailField.setDisable(false);
    }
    @FXML
    public void onEditPhoneClick(){
        disableAll();
        phoneField.setDisable(false);
    }
    @FXML
    public void onEditCnpClick(){
        disableAll();
        cnpField.setDisable(false);
    }
    @FXML
    public void onEditIbanClick(){
        disableAll();
        ibanField.setDisable(false);
    }
    @FXML
    public void onEditCountryClick(){
        disableAll();
        countryField.setDisable(false);
    }
    @FXML
    public void onEditRegionClick(){
        disableAll();
        regionField.setDisable(false);
    }
    @FXML
    public void onEditTownClick(){
        disableAll();
        townField.setDisable(false);
    }
    @FXML
    public void onEditStreetAddressClick(){
        disableAll();
        streetAddressField.setDisable(false);
    }
    @FXML
    public void onEditPostalCodeClick(){
        disableAll();
        postalCodeField.setDisable(false);
    }
    @FXML
    public void onEditContractNumberClick(){
        disableAll();
        contractNumberField.setDisable(false);
    }
    void disableAllSuper(){
         firstNameField.setDisable(true);
         lastNameField.setDisable(true);
         emailField.setDisable(true);
         phoneField.setDisable(true);
         cnpField.setDisable(true);
         ibanField.setDisable(true);
         countryField.setDisable(true);
         regionField.setDisable(true);
         townField.setDisable(true);
         streetAddressField.setDisable(true);
         postalCodeField.setDisable(true);
         contractNumberField.setDisable(true);
    }
    abstract void disableAll();
    abstract void onBackButtonClick();
    abstract void onSaveChangesButtonClick();
}
