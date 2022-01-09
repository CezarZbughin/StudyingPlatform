package com.StudyingPlatform.controllers;

import com.StudyingPlatform.application.StudyingApplication;
import com.StudyingPlatform.model.User;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class SuperController {
    public static User activeUser;

    public static void popError(String message)throws IOException {
        Stage stage = new Stage();
        URL url = StudyingApplication.class.getResource("error-pop-up.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(url);
        Parent root = (Parent)fxmlLoader.load();
        ErrorController controller = fxmlLoader.getController();
        controller.create(message,stage);
        Scene scene = new Scene(root,270, 70);
        stage.setTitle("Error");
        stage.setScene(scene);
        stage.show();
    }
}
