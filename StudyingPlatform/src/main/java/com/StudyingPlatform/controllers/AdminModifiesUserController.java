package com.StudyingPlatform.controllers;

import com.StudyingPlatform.application.StudyingApplication;
import com.StudyingPlatform.model.User;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

abstract class AdminModifiesUserController{
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

    private User displayedUser;

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

    @FXML
    public void onBackButtonClick() throws IOException {
        Stage stage = StudyingApplication.getPrimaryStage();
        URL url = StudyingApplication.class.getResource("user-list-view.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(url);
        Scene scene = new Scene(fxmlLoader.load(), 400, 500);
        stage.setScene(scene);
    }

    abstract void disableAll();
    abstract void onSaveChangesButtonClick() throws IOException ;
    abstract void updateView();

    public void setUser(User user){
        displayedUser = user;
        updateView();
    }

    void updateSuper(){
        firstNameField.setText(displayedUser.getFirstName());
        lastNameField.setText(displayedUser.getLastName());
        emailField.setText(displayedUser.getEmail());
        phoneField.setText(displayedUser.getPhone());
        cnpField.setText(displayedUser.getCnp());
        ibanField.setText(displayedUser.getIban());
        countryField.setText(displayedUser.getAddress().getCountry());
        regionField.setText(displayedUser.getAddress().getRegion());
        townField.setText(displayedUser.getAddress().getTown());
        streetAddressField.setText(displayedUser.getAddress().getStreetAddress());
        postalCodeField.setText(displayedUser.getAddress().getPostalCode());
        contractNumberField.setText(displayedUser.getContractNumber());
        //title.setText("ok");
    }

    public User getDisplayedUser() {
        return displayedUser;
    }

    public void updateDisplayedUser(){
        displayedUser.setFirstName(firstNameField.getText());
        displayedUser.setLastName(lastNameField.getText());
        displayedUser.setEmail(emailField.getText());
        displayedUser.setPhone(phoneField.getText());
        displayedUser.setCnp(cnpField.getText());
        displayedUser.setIban(ibanField.getText());
        displayedUser.getAddress().setCountry(countryField.getText());
        displayedUser.getAddress().setRegion(regionField.getText());
        displayedUser.getAddress().setTown(townField.getText());
        displayedUser.getAddress().setStreetAddress(streetAddressField.getText());
        displayedUser.getAddress().setPostalCode(postalCodeField.getText());
        displayedUser.setContractNumber(contractNumberField.getText());
    }

}
