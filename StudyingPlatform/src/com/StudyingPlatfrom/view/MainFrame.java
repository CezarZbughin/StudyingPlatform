package com.StudyingPlatfrom.view;

import javax.swing.*;

public class MainFrame {
    private static JFrame mainFrame;
    static{
        mainFrame = new JFrame("StudyingPlatform");
    }

    public static JFrame getFrame(){
        return mainFrame;
    }
}
