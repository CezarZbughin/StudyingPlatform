package com.StudyingPlatform.controllers;


import com.StudyingPlatform.application.StudyingApplication;
import com.StudyingPlatform.model.Professor;
import com.StudyingPlatform.model.Student;
import com.StudyingPlatform.model.SubjectProfessor;
import com.StudyingPlatform.service.Exceptions.SubjectNotFoundException;
import com.StudyingPlatform.service.ProfessorService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AddGradesController implements Initializable {
    @FXML
    VBox subjectsVBox;

    private List<Student> listedStudents;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {};
       /* try {
            listedStudents = ProfessorService.getStudentsBySubject((Professor) SuperController.activeUser);
        } catch (SQLException e) {
            listedStudents = new ArrayList<>();
        } catch (SubjectNotFoundException e) {
            listedStudents = new ArrayList<>();
        }
        try {
            updateList();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
    }

    private void updateList() throws IOException {
        subjectsVBox.getChildren().clear();
        for (Student student : listedStudents) {
            URL url = StudyingApplication.class.getResource("catalog-row.fxml");
            FXMLLoader fxmlLoader = new FXMLLoader(url);
            Parent row = (Parent) fxmlLoader.load();
            AddGradesRowController controller = fxmlLoader.<AddGradesRowController>getController();
            controller.setStudent(student);
            subjectsVBox.getChildren().add(row);
        }
    }
    */


    @FXML
    public void onBackButtonClick() throws IOException {
        URL url;
        Stage stage = StudyingApplication.getPrimaryStage();
        url = StudyingApplication.class.getResource("Catalog.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(url);
        Scene scene = new Scene(fxmlLoader.load(), 400, 500);
        stage.setScene(scene);

    }
    @FXML
    public void onDownloadButtonClick() throws IOException {
        URL url;
        Stage stage = StudyingApplication.getPrimaryStage();
        url = StudyingApplication.class.getResource("Catalog.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(url);
        Scene scene = new Scene(fxmlLoader.load(), 400, 500);
        stage.setScene(scene);

    }
    @FXML
    public void onSaveChangesButtonClick() throws IOException {
        URL url;
        Stage stage = StudyingApplication.getPrimaryStage();
        url = StudyingApplication.class.getResource("Catalog.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(url);
        Scene scene = new Scene(fxmlLoader.load(), 400, 500);
        stage.setScene(scene);

    }
}