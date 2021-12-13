package com.StudyingPlatfrom.service;

import com.StudyingPlatfrom.model.Student;
import com.StudyingPlatfrom.model.User;
import com.StudyingPlatfrom.view.GreenView;
import com.StudyingPlatfrom.view.MainFrame;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserService {
    public static int logIn(String username,String password){
        Connection connection = DataBaseService.getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from user where username = \"" + username + "\" AND password = \"" + password + "\"");

            if(resultSet.next()) {
                MainFrame.setView(new GreenView());
                return resultSet.getInt("id");
            }
            return -1;
        }
        catch (SQLException e){
            return -1;
        }
    }

    public static Student map(ResultSet resultSet){
        try{
            Student student = new Student();
            student.setId(resultSet.getInt("id"));
            return student;
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }
}
