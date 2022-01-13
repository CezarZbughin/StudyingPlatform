package com.StudyingPlatform.application;
import com.StudyingPlatform.controllers.SuperController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;

public class StudyingApplication extends Application {
    private static Stage primaryStage;
    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;
        primaryStage.setTitle("Studying Platform");
        StudyingApplication.jumpToView("log-in-view.fxml");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void jumpToView(String view){
        try {
            URL url = StudyingApplication.class.getResource(view);
            FXMLLoader fxmlLoader = new FXMLLoader(url);
            Scene scene = new Scene(fxmlLoader.load(), 400, 500);
            primaryStage.setScene(scene);
        }catch (IOException e){
            SuperController.popError("Failed to jump to " + view);
            e.printStackTrace();
        }
    }
}

