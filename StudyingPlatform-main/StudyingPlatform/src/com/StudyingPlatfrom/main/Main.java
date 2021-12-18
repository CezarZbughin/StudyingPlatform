package com.StudyingPlatfrom.main;

import com.StudyingPlatfrom.model.Student;
import com.StudyingPlatfrom.service.DataBaseService;
import com.StudyingPlatfrom.service.StudentService;
import com.StudyingPlatfrom.view.GreenView;
import com.StudyingPlatfrom.view.LogInView;
import com.StudyingPlatfrom.view.MainFrame;

import java.sql.*;

public class Main {
    public static void main(String[] args) {
        MainFrame.setView(new LogInView());
        Connection connection = DataBaseService.getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from sudent");

            StudentService studentService=new StudentService();
            studentService.mapUser(resultSet);



        }catch (SQLException e){
            e.printStackTrace();
        }

    }
}