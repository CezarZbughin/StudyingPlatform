package com.StudyingPlatfrom.view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class TestView extends JPanel {
    private JLabel filler2;
    private JLabel filler3;
    private JLabel filler4;
    private JLabel filler5;
    //header
    private JLabel header;
    //title
    private JPanel titlePanel;
    private JLabel title;
    //username panel
    private JPanel usernamePanel;
    private JLabel usernameLabel;
    private JTextField usernameText;
    //password panel
    private JPanel passwordPanel;
    private JLabel passwordLabel;
    private JTextField passwordText;
    //buttons
    private JPanel buttonsPanel;
    private JButton logInButton;
    private JButton signUpButton;
    //center panel
    private JPanel centerPanel;

    public TestView() {
        //header
        header = new JLabel(" ");
        //title
        title = new JLabel("Studying Platform");
        titlePanel = new JPanel();
        titlePanel.add(title);
        //username Panel
        usernamePanel = new JPanel();
        usernameLabel = new JLabel("Username:");
        usernameText = new JTextField(11);
        usernamePanel.add(usernameLabel);
        usernamePanel.add(usernameText);
        usernamePanel.setLayout(new FlowLayout());
        //password Panel
        passwordPanel = new JPanel();
        passwordLabel = new JLabel("Password:");
        passwordText = new JTextField(11);
        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordText);
        passwordPanel.setLayout(new FlowLayout());
        //buttons
        logInButton = new JButton("Log In");
        signUpButton = new JButton("Sign Up");
        buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new FlowLayout());
        buttonsPanel.add(logInButton);
        buttonsPanel.add(signUpButton);
        //center panel
        centerPanel = new JPanel();
        centerPanel.add(header);
        centerPanel.add(titlePanel);
        centerPanel.add(usernamePanel);
        centerPanel.add(passwordPanel);
        centerPanel.add(buttonsPanel);
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        //construct components
        filler2 = new JLabel (" ");
        filler3 = new JLabel (" ");
        filler4 = new JLabel ("                              ");
        filler5 = new JLabel ("                              ");
        //adjust size and set layout
        BorderLayout layout = new BorderLayout(0, 0);
        setLayout (layout);
        //add components
        add (centerPanel, BorderLayout.CENTER);
        add (filler2, BorderLayout.SOUTH);
        add (filler3, BorderLayout.NORTH);
        add (filler4, BorderLayout.EAST);
        add (filler5, BorderLayout.WEST);
    }
}
