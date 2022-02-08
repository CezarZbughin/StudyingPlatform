package com.StudyingPlatform.controllers;

import com.StudyingPlatform.application.StudyingApplication;
import com.StudyingPlatform.model.Group;
import com.StudyingPlatform.model.Message;
import com.StudyingPlatform.model.User;
import com.StudyingPlatform.service.DataBaseService;
import com.StudyingPlatform.service.Exceptions.EmptyResultSetException;
import com.StudyingPlatform.service.Exceptions.MessageNotFoundException;
import com.StudyingPlatform.service.Exceptions.UserNotFoundException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ChatController implements Initializable {
    @FXML
    private VBox messageVBox;
    @FXML
    private VBox groupsVBox;
    @FXML
    private TextField messageTextField;
    @FXML
    private ScrollPane messageScroll;
    @FXML
    private Label groupNameLabel;
    @FXML
    private MenuItem viewMembers;
    @FXML
    private MenuItem leaveGroup;


    private Group selectedGroup;
    private InboxRowController selectedInboxRow;

    private Timestamp lastLoadedMessageTime;
    private boolean shouldScroll = false;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        inboxInit();
        messageScroll.needsLayoutProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue && shouldScroll) {
                messageScroll.setVvalue(1.0);
            }
        });
    }

    @FXML
    public void onCreateButtonClick() {
        StudyingApplication.jumpToView("create-group.fxml");
    }

    @FXML
    public void onBackButtonClick() {
        StudyingApplication.jumpToView("home.fxml");
    }

    @FXML
    public void onSendButtonClick() throws IOException {
        if (messageTextField.getText().equals("") || selectedGroup == null)
            return;
        try {
            DataBaseService.sendMessage(
                    messageTextField.getText(),
                    selectedGroup,
                    new Timestamp(System.currentTimeMillis()),
                    SuperController.activeUser
            );
            Message sentMessage = new Message(
                    0,
                    messageTextField.getText(),
                    selectedGroup,
                    new Timestamp(System.currentTimeMillis()),
                    SuperController.activeUser
            );
            shouldScroll = true;
            insertMessage(messageVBox.getChildren().size(), sentMessage);
            messageTextField.setText("");
        } catch (SQLException e) {
            e.printStackTrace();
            SuperController.popError("Couldn\'t send message.");
            return;
        }
    }

    private class JoinButton extends HBox {
        JoinButton() {
            groupNameLabel.setText(selectedGroup.getName());
            Button button = new Button("join");
            button.setPrefHeight(40);
            button.setPrefWidth(80);
            button.setOnAction(actionEvent -> {
                if (selectedGroup == null)
                    return;
                try {
                    DataBaseService.userJoinGroup(SuperController.activeUser, selectedGroup);
                } catch (SQLException e) {
                    e.printStackTrace();
                    SuperController.popError("Couldn\'t join the group.");
                    return;
                }
                StudyingApplication.jumpToView("chat.fxml",550,500);
            });
            this.setMinHeight(365);
            this.setMinWidth(330);
            this.setAlignment(Pos.CENTER);
            this.getChildren().add(button);
        }
    }

    private class ShowMoreButton extends HBox {
        ShowMoreButton() {
            Button button = new Button("show more");
            button.setPrefHeight(40);
            button.setPrefWidth(80);
            button.setOnAction(actionEvent -> {
                shouldScroll = false;
                if (selectedGroup == null || lastLoadedMessageTime == null)
                    return;
                try {
                    List<Message> messages = DataBaseService.getMessages(selectedGroup, lastLoadedMessageTime, 10);
                    lastLoadedMessageTime = messages.get(messages.size() - 1).getTimeSent();
                    insertMessages(messages);
                } catch (SQLException | MessageNotFoundException e) {
                    return;
                }
            });
            this.setMinHeight(90);
            this.setMinWidth(330);
            this.setAlignment(Pos.CENTER);
            this.getChildren().add(button);
        }
    }

    private void inboxInit() {
        List<Group> usersGroups;
        try {
            usersGroups = DataBaseService.getUserGroups(SuperController.activeUser);
        } catch (EmptyResultSetException e) {
            usersGroups = new ArrayList<>();
        } catch (SQLException e) {
            SuperController.popError("Something went wrong trying to load you groups");
            usersGroups = new ArrayList<>();
            e.printStackTrace();
        }
        List<Group> joinableGroups;
        try {
            joinableGroups = DataBaseService.getUserJoinableGroups(SuperController.activeUser);
        } catch (EmptyResultSetException e) {
            joinableGroups = new ArrayList<>();
        } catch (SQLException e) {
            SuperController.popError("Something went wrong trying to load new groups");
            joinableGroups = new ArrayList<>();
            e.printStackTrace();
        }
        groupsVBox.getChildren().clear();
        URL url = StudyingApplication.class.getResource("inbox-row.fxml");
        for (Group g : usersGroups) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(url);
                Parent row = (Parent) fxmlLoader.load();
                InboxRowController controller = fxmlLoader.<InboxRowController>getController();
                controller.setGroup(g, true, this);
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
                controller.setGroup(g, false, this);
                groupsVBox.getChildren().add(row);
            } catch (IOException e) {
                //continue
            }
        }
    }

    public void messagesListInit() {
        if (selectedGroup == null)
            return;

        messageVBox.getChildren().clear();
        messageVBox.getChildren().add(new ShowMoreButton());
        try {
            List<Message> messages = DataBaseService.getMessages(
                    selectedGroup,
                    new Timestamp(System.currentTimeMillis() + 10),
                    10
            );
            groupNameLabel.setText(selectedGroup.getName());
            lastLoadedMessageTime = messages.get(messages.size() - 1).getTimeSent();
            insertMessages(messages);
            messageScroll.setVvalue(1.0);
        } catch (SQLException | MessageNotFoundException e) {
            return;
        }
    }

    public void showJoinButton() {
        messageVBox.getChildren().clear();
        messageVBox.getChildren().add(new JoinButton());
    }

    public void onViewMembersClick() throws UserNotFoundException, SQLException, EmptyResultSetException {
        messageVBox.getChildren().clear();
        messageVBox.getChildren().add(new Label("Members:"));
        List<String> members = DataBaseService.getStudentMembersGroup(selectedGroup.getId());

        for (String m : members) {
            System.out.println(m);
            messageVBox.getChildren().add(new Label(m));
        }
        List<String> professorMembers = DataBaseService.getProfessorMembersGroup(selectedGroup.getId());

        for (String m : professorMembers) {
            System.out.println(m);
            messageVBox.getChildren().add(new Label(m));
        }
    }
    public void onLeaveGroupClick() throws SQLException {
        if(SuperController.activeUser.getRole().equals("STUDENT"))
        DataBaseService.studentLeaveGroup(SuperController.activeUser.getId(),selectedGroup.getId());
        else{
            DataBaseService.professorLeaveGroup(SuperController.activeUser.getId(),selectedGroup.getId());
        }


    }
    public void onAddActivityClick(){}



    private void insertMessage(int index, Message message) {
        try {
            URL url = StudyingApplication.class.getResource("message.fxml");
            FXMLLoader fxmlLoader = new FXMLLoader(url);
            Parent row = (Parent) fxmlLoader.load();
            MessageController controller = fxmlLoader.<MessageController>getController();
            controller.set(
                    message.getText(),
                    message.getUser().getUsername(),
                    message.getUser().getId() == SuperController.activeUser.getId()
            );
            messageVBox.getChildren().add(index, row);
        } catch (IOException e) {
            return;
        }
    }

    private void insertMessages(List<Message> messages) {
        for (Message message : messages) {
            insertMessage(1, message);
        }
    }

    public void setSelectedGroup(Group group) {
        this.selectedGroup = group;
    }

    public void setSelectedInboxRow(InboxRowController selectedInboxRow) {
        this.selectedInboxRow = selectedInboxRow;
    }

    public InboxRowController getSelectedInboxRow() {
        return selectedInboxRow;
    }
}
