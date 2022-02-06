package com.StudyingPlatform.controllers;

import com.StudyingPlatform.model.Group;
import com.StudyingPlatform.service.DataBaseService;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

import java.sql.SQLException;

public class InboxRowController {
    @FXML
    private Label name;

    private Group group;
    private boolean isJoined;
    private ChatController parentController;

    public void setGroup(Group g, boolean isJoined, ChatController parentController) {
        this.group = g;
        this.isJoined = isJoined;
        this.parentController = parentController;
        name.setText(g.getName());
        if(!isJoined){
            Font font = Font.font("System", FontWeight.NORMAL, FontPosture.ITALIC, 14);
            name.setFont(font);
        }else{
            Font font = Font.font("System", FontWeight.BOLD, FontPosture.REGULAR, 14);
            name.setFont(font);
        }
    }

    @FXML
    public void onMousePressed() throws SQLException {
        parentController.setSelectedGroup(group);
        if(isJoined){
            parentController.loadMessages(group);
        }else{
            parentController.showJoinButton();
        }
    }
}