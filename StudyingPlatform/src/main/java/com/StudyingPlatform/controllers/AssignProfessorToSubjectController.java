package com.StudyingPlatform.controllers;

import com.StudyingPlatform.application.StudyingApplication;
import com.StudyingPlatform.model.Professor;
import com.StudyingPlatform.model.Subject;
import com.StudyingPlatform.model.User;
import com.StudyingPlatform.service.DataBaseService;
import com.StudyingPlatform.service.Exceptions.SubjectNotFoundException;
import com.StudyingPlatform.service.Exceptions.UserNotFoundException;
import com.StudyingPlatform.service.ProfessorService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AssignProfessorToSubjectController implements Initializable {
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField selectedProfessorField;
    @FXML
    private VBox professorsVBox;
    @FXML
    private TextField subjectNameField;
    @FXML
    private TextField selectedSubjectField;
    @FXML
    private VBox subjectsVBox;

    private List<User> listedProfessors;
    private List<Subject> listedSubjects;
    private Professor selectedProfessor;
    private Subject selectedSubject;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            listedProfessors = DataBaseService.getAllProfessor();
            listedSubjects = DataBaseService.getAllSubjects();
        } catch (UserNotFoundException e) {
            listedProfessors = new ArrayList<>();
            System.out.println("no professor found");
        } catch (SubjectNotFoundException e) {
            listedSubjects = new ArrayList<>();
            System.out.println("no subject found");
        } catch (SQLException e) {
            System.out.println("something went wrong in assign professor to subject initialize");
            e.printStackTrace();
            return;
        }
        try {
            updateView();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onSearchProfessorButtonClick() throws IOException {
        try {
            List<User> namedUsers = DataBaseService.getUsersByName(firstNameField.getText(), lastNameField.getText());
            listedProfessors = new ArrayList<>();
            for (User user : namedUsers) {
                if ("PROFESSOR".equals(user.getRole())) {
                    listedProfessors.add(user);
                }
            }
        } catch (UserNotFoundException e) {
            listedProfessors = new ArrayList<>();
            System.out.println("no professor found");
        } catch (SQLException e) {
            System.out.println("something went wrong in searching for professor");
            e.printStackTrace();
            return;
        }
        updateView();
    }

    public void onSearchSubjectButtonClick() throws IOException {
        try {
            listedSubjects = DataBaseService.getSubjectsByName(subjectNameField.getText());
        } catch (SubjectNotFoundException e) {
            listedProfessors = new ArrayList<>();
            System.out.println("no subject found");
        } catch (SQLException e) {
            System.out.println("something went wrong in searching for professor");
            e.printStackTrace();
            return;
        }
        updateView();
    }

    public void onCancelButtonClick() throws IOException {
        Stage stage = StudyingApplication.getPrimaryStage();
        URL url = StudyingApplication.class.getResource("admin-view.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(url);
        Scene scene = new Scene(fxmlLoader.load(), 400, 500);
        stage.setScene(scene);
    }

    public void onAssignButtonClick() throws IOException {
        try {
            ProfessorService.assignProfessorToSubject(selectedProfessor, selectedSubject);
            Stage stage = StudyingApplication.getPrimaryStage();
            URL url = StudyingApplication.class.getResource("admin-view.fxml");
            FXMLLoader fxmlLoader = new FXMLLoader(url);
            Scene scene = new Scene(fxmlLoader.load(), 400, 500);
            stage.setScene(scene);
        } catch (SQLException e) {
            System.out.println("Something went wrong trying to assign professor to subject");
            e.printStackTrace();
        }
    }

    private void updateView() throws IOException {
        subjectsVBox.getChildren().clear();
        for (Subject subject : listedSubjects) {
            URL url = StudyingApplication.class.getResource("assign-professor-to-subject-row.fxml");
            FXMLLoader fxmlLoader = new FXMLLoader(url);
            Parent row = (Parent) fxmlLoader.load();
            AssignProfessorToSubjectRowController controller = fxmlLoader.<AssignProfessorToSubjectRowController>getController();
            controller.setSubject(subject, this);
            subjectsVBox.getChildren().add(row);
        }
        professorsVBox.getChildren().clear();
        for (User user : listedProfessors) {
            URL url = StudyingApplication.class.getResource("assign-professor-to-subject-row.fxml");
            FXMLLoader fxmlLoader = new FXMLLoader(url);
            Parent row = (Parent) fxmlLoader.load();
            AssignProfessorToSubjectRowController controller = fxmlLoader.<AssignProfessorToSubjectRowController>getController();
            controller.setProfessor((Professor) user, this);
            professorsVBox.getChildren().add(row);
        }
    }

    public void setSelectedProfessor(Professor professor) {
        selectedProfessor = professor;
        selectedProfessorField.setText(professor.getFirstName() + " " + professor.getLastName());
    }

    public void setSelectedSubject(Subject subject) {
        selectedSubject = subject;
        selectedSubjectField.setText(subject.getName());
    }
}
