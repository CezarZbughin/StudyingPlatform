package com.StudyingPlatform.controllers;

import com.StudyingPlatform.application.StudyingApplication;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeStudentController implements Initializable {
    @FXML
    MenuButton menuButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        menuButton.setText(SuperController.activeUser.getUsername());
        if(SuperController.activeUser.isAdmin() || SuperController.activeUser.isSuperAdmin()){
            MenuItem menuItem = new MenuItem("Administrate");
            menuItem.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    StudyingApplication.jumpToView("admin-view.fxml");
                }
            });
            menuButton.getItems().add(menuItem);
        }
    }

    @FXML
    public void onTodayButtonClick() throws IOException {
        StudyingApplication.jumpToView("today.fxml");
    }
    @FXML
    public void onCatalogButtonClick() throws IOException {}
    @FXML
    public void onCoursesButtonClick() throws IOException {
        Stage stage = StudyingApplication.getPrimaryStage();
        URL url = StudyingApplication.class.getResource("student-courses.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(url);
        Scene scene = new Scene(fxmlLoader.load(), 400, 500);
        stage.setScene(scene);
    }
    @FXML
    public void onProfileActionClick(){
        StudyingApplication.jumpToView("display-student-data.fxml");
    }
    @FXML
    public void onLogOutActionClick(){
        StudyingApplication.jumpToView("log-in-view.fxml");
        SuperController.activeUser = null;
    }


}
