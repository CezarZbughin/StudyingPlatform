package com.StudyingPlatfrom.view;

import javax.swing.*;

public class MainFrame {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 500;

    private static JFrame mainFrame;
    private static JPanel mainPanel;
    private static MainFrame instance;
    static{
        mainFrame = new JFrame();
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(WIDTH, HEIGHT);
    }

    public MainFrame(){
        mainFrame = new JFrame();
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(WIDTH, HEIGHT);
    }

    public static MainFrame getInstance(){
        if(instance == null){
           instance = new MainFrame();
        }
        return instance;
    }
    public JFrame getFrame(){
        return mainFrame;
    }

    public static void setView(JPanel p){
        if(mainPanel == null){
            mainPanel = p;
            mainFrame.add(mainPanel);
        }
        else{
            mainFrame.remove(mainPanel);
            mainPanel = p;
            mainFrame.add(mainPanel);
        }
        mainFrame.setVisible(true);
    }
}
