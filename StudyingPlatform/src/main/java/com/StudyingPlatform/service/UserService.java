package com.StudyingPlatform.service;

import com.StudyingPlatform.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserService{
    public static User mapResultSet(ResultSet resultSet){
        User user = null;
        try {
            if (resultSet.next()) {
                String role = resultSet.getString("role");
                switch (role){
                    case "STUDENT":
                        resultSet.previous();
                        user = StudentService.mapResultSet(resultSet);
                        break;
                    case "PROFESSOR":
                        resultSet.previous();
                        user = ProfessorService.mapResultSet(resultSet);
                        break;
                    default:
                        return null;
                }
            }
        }catch (SQLException e){
            return null;
        }
        return user;
    }
}