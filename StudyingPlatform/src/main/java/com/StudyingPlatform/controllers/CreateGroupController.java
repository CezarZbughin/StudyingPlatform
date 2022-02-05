package com.StudyingPlatform.controllers;

import com.StudyingPlatform.application.StudyingApplication;
import com.StudyingPlatform.model.Subject;
import com.StudyingPlatform.service.DataBaseService;
import com.StudyingPlatform.service.Exceptions.EmptyResultSetException;
import com.StudyingPlatform.service.Exceptions.UserNotFoundException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CreateGroupController implements Initializable {
    @FXML
    private ComboBox<Subject> selectSubject;
    @FXML
    private TextField name;
    @FXML
    public void onBackButtonClick() throws IOException {
        Stage stage = StudyingApplication.getPrimaryStage();
        URL url = StudyingApplication.class.getResource("chat.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(url);
        Scene scene = new Scene(fxmlLoader.load(), 400, 500);
        stage.setScene(scene);
    }
    @FXML
    public void onCreateButtonClick() throws IOException {
        try{
            Subject subject=selectSubject.getSelectionModel().getSelectedItem();
            DataBaseService.createGroup(name.getText(),subject.getId());
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        Stage stage = StudyingApplication.getPrimaryStage();
        URL url = StudyingApplication.class.getResource("chat.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(url);
        Scene scene = new Scene(fxmlLoader.load(), 400, 500);
        stage.setScene(scene);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
          selectSubject.setItems(FXCollections.observableArrayList(DataBaseService.getSubjectsByIdStudent(SuperController.activeUser.getId())));
        } catch (SQLException | EmptyResultSetException e) {
            e.printStackTrace();
        }

    }
}
