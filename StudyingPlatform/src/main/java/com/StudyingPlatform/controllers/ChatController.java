package com.StudyingPlatform.controllers;

import com.StudyingPlatform.application.StudyingApplication;
import com.StudyingPlatform.model.Group;
import com.StudyingPlatform.model.Message;
import com.StudyingPlatform.service.DataBaseService;
import com.StudyingPlatform.service.Exceptions.EmptyResultSetException;
import com.StudyingPlatform.service.Exceptions.MessageNotFoundException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.*;

public class ChatController implements Initializable {
    @FXML
    private Label groupName;
    @FXML
    private VBox messageVBox;
    @FXML
    private VBox groupsVBox;
    @FXML
    private TextField messageTextField;
    @FXML
    private ScrollPane messageScroll;

    private Group selectedGroup;
    private List<Group> usersGroups;
    private List<Group> joinableGroups;
    private Timestamp lastLoadedMessageTime;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            usersGroups = DataBaseService.getUserGroups(SuperController.activeUser);
        } catch (EmptyResultSetException e) {
            usersGroups = new ArrayList<>();
        } catch (SQLException e){
            SuperController.popError("Something went wrong trying to load you groups");
            usersGroups = new ArrayList<>();
            e.printStackTrace();
        }
        try {
            joinableGroups = DataBaseService.getUserJoinableGroups(SuperController.activeUser);
        } catch (EmptyResultSetException e) {
            joinableGroups = new ArrayList<>();
        } catch (SQLException e){
            SuperController.popError("Something went wrong trying to load new groups");
            joinableGroups = new ArrayList<>();
            e.printStackTrace();
        }
        updateInbox();
    }

    @FXML
    public void onCreateButtonClick() {
        StudyingApplication.jumpToView("create-group.fxml");
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
    public void onSendButtonClick() throws IOException {
        if(messageTextField.getText().equals("") || selectedGroup == null)
            return;
        try{
            DataBaseService.sendMessage(
                    messageTextField.getText(),
                    selectedGroup,
                    new Timestamp(System.currentTimeMillis()),
                    SuperController.activeUser
            );
            insertMessage(
                    messageVBox.getChildren().size()-1,
                    messageTextField.getText(),
                    SuperController.activeUser.getUsername(),
                    true
            );
            messageTextField.setText("");
        }catch (SQLException e){
            e.printStackTrace();
            SuperController.popError("Coudn\'t send message.");
            return;
        }
    }

    private void updateInbox() {
        groupsVBox.getChildren().clear();
        URL url = StudyingApplication.class.getResource("inbox-row.fxml");
        for (Group g : usersGroups) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(url);
                Parent row = (Parent) fxmlLoader.load();
                InboxRowController controller = fxmlLoader.<InboxRowController>getController();
                controller.setGroup(g,true,this);
                groupsVBox.getChildren().add(row);
            } catch (IOException e) {
                //continue
            }
        }
        for (Group g : joinableGroups) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(url);
                Parent row = (Parent) fxmlLoader.load();
                InboxRowController controller = fxmlLoader.<InboxRowController>getController();
                controller.setGroup(g,false,this);
                groupsVBox.getChildren().add(row);
            } catch (IOException e) {
                //continue
            }
        }
    }

    public void loadMessages(Group g){
        messageVBox.getChildren().clear();
        HBox wrap = makeWrappedButton("see more", 80, 40, 50, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(lastLoadedMessageTime == null)return;
                try {
                    List<Message> messages = DataBaseService.getMessages(g, lastLoadedMessageTime, 10);
                    lastLoadedMessageTime = messages.get(messages.size()-1).getTimeSent();
                    for(Message message:messages){
                        insertMessage(
                                1,
                                message.getText(),
                                message.getUser().getUsername(),
                                SuperController.activeUser.getId() == message.getUser().getId()
                        );
                    }
                }catch (SQLException | MessageNotFoundException e){
                    return;
                }
            }
        });
        messageVBox.getChildren().add(wrap);
        List<Message> messages;
        try {
            messages = DataBaseService.getMessages(g, new Timestamp(System.currentTimeMillis()+10), 10);
            lastLoadedMessageTime = messages.get(messages.size()-1).getTimeSent();
        }catch (SQLException | MessageNotFoundException e) {
            messages = new ArrayList<>();
        }

        for(Message message: messages){
            insertMessage(
                    1,
                    message.getText(),
                    message.getUser().getUsername(),
                    SuperController.activeUser.getId() == message.getUser().getId()
                    );
        }
        messageScroll.setVvalue(1.0);
    }

    public void showJoinButton(){
        messageVBox.getChildren().clear();
        HBox wrap = makeWrappedButton("Join", 80, 40, 160, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println(selectedGroup.getName() + "joining");
            }
        });
        messageVBox.getChildren().add(wrap);
    }

    private HBox makeWrappedButton(String text, int xSize,int ySize,int wrapperHeight, EventHandler<ActionEvent> eventEventHandler){
        Button joinButton = new Button(text);
        joinButton.setPrefHeight(ySize);
        joinButton.setPrefWidth(xSize);
        joinButton.setOnAction(eventEventHandler);
        HBox wrap = new HBox();
        wrap.setMinHeight(wrapperHeight);
        wrap.setMinWidth(330);
        wrap.setAlignment(Pos.CENTER);
        wrap.getChildren().add(joinButton);
        return wrap;
    }

    private void insertMessage(int index,String text,String username,boolean isRight){
        try {
            URL url = StudyingApplication.class.getResource("message.fxml");
            FXMLLoader fxmlLoader = new FXMLLoader(url);
            Parent row = (Parent) fxmlLoader.load();
            MessageController controller = fxmlLoader.<MessageController>getController();
            controller.set(text,username,isRight);
            messageVBox.getChildren().add(index,row);
        }catch (IOException e){
            //nothing
        }
    }
    public void setSelectedGroup(Group group){
        this.selectedGroup = group;
    }
}
