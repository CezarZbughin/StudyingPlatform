package com.StudyingPlatfrom.view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class LogInView extends JPanel {
    private JLabel jcomp1;
    private JLabel jcomp2;
    private JTextField jcomp3;
    private JLabel jcomp4;
    private JTextField jcomp5;
    private JButton jcomp6;
    private JButton jcomp7;

    public LogInView() {
        //construct components
        jcomp1 = new JLabel ("Studying PLatfrom");
        jcomp2 = new JLabel ("Login:");
        jcomp3 = new JTextField (5);
        jcomp4 = new JLabel ("Password:");
        jcomp5 = new JTextField (5);
        jcomp6 = new JButton ("Log In");
        jcomp7 = new JButton ("Sign Up");

        //adjust size and set layout
        setPreferredSize (new Dimension (624, 334));
        setLayout (null);

        //add components
        add (jcomp1);
        add (jcomp2);
        add (jcomp3);
        add (jcomp4);
        add (jcomp5);
        add (jcomp6);
        add (jcomp7);

        //set component bounds (only needed by Absolute Positioning)
        jcomp1.setBounds (40, 5, 140, 65);
        jcomp2.setBounds (25, 75, 65, 25);
        jcomp3.setBounds (100, 75, 100, 25);
        jcomp4.setBounds (0, 115, 100, 25);
        jcomp5.setBounds (100, 115, 100, 25);
        jcomp6.setBounds (15, 165, 100, 25);
        jcomp7.setBounds (125, 165, 100, 25);
    }
}
