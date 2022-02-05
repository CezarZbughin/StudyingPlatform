package com.StudyingPlatform.controllers;

import com.StudyingPlatform.application.StudyingApplication;
import com.StudyingPlatform.model.Group;
import com.StudyingPlatform.model.Student;
import com.StudyingPlatform.model.Subject;
import com.StudyingPlatform.model.SubjectStudent;
import com.StudyingPlatform.service.DataBaseService;
import com.StudyingPlatform.service.Exceptions.EmptyResultSetException;
import com.StudyingPlatform.service.StudentService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class ChatController  implements Initializable {
    private List<Group> groups;
    private Integer selectedGroupId;
    @FXML
    private Label groupName;
    @FXML
    private VBox messageVBox1;
    @FXML
    private VBox groupsVBox;
    @FXML
    private TextField textField;
    @FXML
    private HBox joinBox;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            groups= DataBaseService.getStudentGroups( SuperController.activeUser.getId());
            updateList();
        } catch (SQLException |IOException | EmptyResultSetException e) {
            e.printStackTrace();
        }
    }
    private void updateList() throws IOException {
        groupsVBox.getChildren().clear();
        for(Group g:groups) {
            URL url = StudyingApplication.class.getResource("inbox.fxml");
            FXMLLoader fxmlLoader = new FXMLLoader(url);
            Parent row = (Parent) fxmlLoader.load();
            InboxController controller = fxmlLoader.<InboxController>getController();
            controller.setLabel(g.getNume());
            controller.setChatController(this);
            controller.setGroup(g);
            controller.setJoinBox(joinBox);
            groupsVBox.getChildren().add(row);
        }
    }

    @FXML
    public void onCreateButtonClick() throws IOException {
        Stage stage = StudyingApplication.getPrimaryStage();
        URL url = StudyingApplication.class.getResource("create-group.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(url);
        Scene scene = new Scene(fxmlLoader.load(), 400, 500);
        stage.setScene(scene);
    }
    @FXML
    public void onBackButtonClick() throws IOException {
        Stage stage = StudyingApplication.getPrimaryStage();
        if ("STUDENT".equals(SuperController.activeUser.getRole())) {
            StudyingApplication.jumpToView("student-courses.fxml");
        } else if ("PROFESSOR".equals(SuperController.activeUser.getRole())) {
            StudyingApplication.jumpToView("home-professor.fxml");
        } else throw new IllegalStateException("Unexpected role for user");
    }
    @FXML
    public void onJoinButtonClick() throws SQLException {
        DataBaseService.insertStudentGroup(SuperController.activeUser.getId(),selectedGroupId);
        joinBox.setVisible(false);
    }
    @FXML
    public void onSendButtonClick() throws IOException {}

    public void setSelectedGroupId(Integer selectedGroupId) {
        this.selectedGroupId = selectedGroupId;
    }
}
