package com.StudyingPlatfrom.main;

import com.StudyingPlatfrom.service.DataBaseService;
import com.StudyingPlatfrom.view.LogInView;
import com.StudyingPlatfrom.view.MainFrame;
import com.StudyingPlatfrom.view.TestView;

import javax.swing.*;
import java.nio.charset.MalformedInputException;
import java.sql.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = MainFrame.getInstance().getFrame();
        frame.add(new TestView());
        frame.setVisible(true);

    }
}