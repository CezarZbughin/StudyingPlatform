package com.StudyingPlatform.controllers;

import com.StudyingPlatform.application.StudyingApplication;
import com.StudyingPlatform.model.Student;
import com.StudyingPlatform.model.User;
import com.StudyingPlatform.service.DataBaseService;
import com.StudyingPlatform.service.Exceptions.UserNotFoundException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.zip.InflaterOutputStream;

public class UserListController implements Initializable {
    @FXML
    VBox usersVBox;
    @FXML
    Button anyButton;
    @FXML
    Button studentButton;
    @FXML
    Button professorButton;
    @FXML
    TextField firstNameField;
    @FXML
    TextField lastNameField;

    List<User> listedUsers;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        try {
            onAnyButtonClick();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void onAnyButtonClick() throws IOException {
        studentButton.setDisable(false);
        professorButton.setDisable(false);
        anyButton.setDisable(true);

        try {
            listedUsers = DataBaseService.getAllUsers();
        }catch(SQLException e){
            e.printStackTrace();
        }catch (UserNotFoundException e){
            //consider showing error here;
            listedUsers = new ArrayList<>();
        }
        updateList();
    }
    @FXML
    public void onStudentButtonClick() throws IOException{
        studentButton.setDisable(true);
        professorButton.setDisable(false);
        anyButton.setDisable(false);

        try {
            listedUsers = DataBaseService.getAllStudents();
        }catch(SQLException e){
            e.printStackTrace();
        }catch (UserNotFoundException e){
            //consider showing error here;
            listedUsers = new ArrayList<>();
        }
        updateList();
    }
    @FXML
    public void onProfessorButtonClick() throws IOException{
        studentButton.setDisable(false);
        professorButton.setDisable(true);
        anyButton.setDisable(false);

        try {
            listedUsers = DataBaseService.getAllProfessor();
        }catch(SQLException e){
            e.printStackTrace();
        }catch (UserNotFoundException e){
            //consider showing error here;
            listedUsers = new ArrayList<>();
        }
        updateList();
    }
    @FXML
    public void onSearchButtonClick() throws IOException{
        studentButton.setDisable(false);
        professorButton.setDisable(false);
        anyButton.setDisable(true);
        try {
            listedUsers = DataBaseService.getUsersByName(firstNameField.getText(),lastNameField.getText());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (UserNotFoundException e) {
            //show error
            listedUsers = new ArrayList<>();
        }
        updateList();
    }
    @FXML
    public void onBackButtonClick() throws IOException{
        Stage stage = StudyingApplication.getPrimaryStage();
        URL url = StudyingApplication.class.getResource("admin-view.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(url);
        Scene scene = new Scene(fxmlLoader.load(), 400, 500);
        stage.setScene(scene);
    }
    private void updateList() throws IOException{
        usersVBox.getChildren().clear();
        for(User user:listedUsers){
            URL url = StudyingApplication.class.getResource("user-list-row.fxml");
            FXMLLoader fxmlLoader = new FXMLLoader(url);
            Parent row = (Parent)fxmlLoader.load();
            UserListRowController controller = fxmlLoader.<UserListRowController>getController();
            controller.setUser(user);
            usersVBox.getChildren().add(row);
        }
    }
}
