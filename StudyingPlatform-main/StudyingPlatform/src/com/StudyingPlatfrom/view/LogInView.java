package com.StudyingPlatfrom.view;

import com.StudyingPlatfrom.service.UserService;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class LogInView extends JPanel {
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
    private JPasswordField passwordText;
    //buttons
    private JPanel buttonsPanel;
    private JButton logInButton;
    private JButton signUpButton;
    //center panel
    private JPanel centerPanel;

    public LogInView() {
        BorderLayout layout = new BorderLayout(0, 0);
        setLayout (layout);
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
        passwordText = new JPasswordField("",11);
        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordText);
        passwordPanel.setLayout(new FlowLayout());
        //buttons
        logInButton = new JButton("Log In");
        logInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserService.logIn(usernameText.getText(),String.valueOf(passwordText.getPassword()));
            }
        });
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
        add (centerPanel, BorderLayout.CENTER);
    }
}
