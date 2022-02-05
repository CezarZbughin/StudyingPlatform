package com.StudyingPlatform.controllers;

import com.StudyingPlatform.model.Group;
import com.StudyingPlatform.model.Subject;
import com.StudyingPlatform.service.DataBaseService;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.sql.SQLException;

public class InboxController {
    private HBox joinBox;
    private Group group;
    private ChatController chatController;
    @FXML
    private Label name;

    public void setLabel(String groupName) {
        name.setText(groupName);
    }

    @FXML
    public void onMousePressed() throws SQLException {
        chatController.setSelectedGroupId(group.getId());
        if (DataBaseService.checkGroup(SuperController.activeUser.getId(), group.getId())) {
            joinBox.setVisible(false);

        }
        else
        {

            joinBox.setVisible(true);
        }
    }



    public void setJoinBox(HBox joinBox) {
        this.joinBox = joinBox;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public void setName(Label name) {
        this.name = name;
    }

    public void setChatController(ChatController chatController) {
        this.chatController = chatController;
    }
}