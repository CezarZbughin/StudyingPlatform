package com.StudyingPlatform.application;

import com.StudyingPlatform.model.Address;
import com.StudyingPlatform.model.Student;
import com.StudyingPlatform.model.User;
import com.StudyingPlatform.service.DataBaseService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;


public class StudyingApplication extends Application {
    private static Stage primaryStage;

    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;
        URL url = StudyingApplication.class.getResource("home-student.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(url);
        Scene scene = new Scene(fxmlLoader.load(), 400, 500);
        stage.setTitle("Studying Platform");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        Student student = new Student(
                0,
                "MihaiAmici3",
                "password",
                "STUDENT",
                "10020321",
                "Mihai",
                "Amici",
                new Address(
                        "Romania",
                        "Sibiu",
                        "Sibiu",
                        "Str.Nucului 47, sc.A",
                        "77434"
                ),
                "073434123",
                "MihaiAmici@gmail.com",
                "102RO2131232",
                "#53051",
                false,
                false,
                4,
                40
        );
        try {
            DataBaseService.insertUser(student);
            System.out.println("inserted with no exception");
        }catch (SQLException e){
            e.printStackTrace();
        }
        launch(args);
    }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }
}