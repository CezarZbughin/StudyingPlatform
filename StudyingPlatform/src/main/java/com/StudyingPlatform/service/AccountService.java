package com.StudyingPlatform.service;

import com.StudyingPlatform.model.User;

import java.sql.*;

public class AccountService {
    private static final String LOG_IN_QUERY = "select * from User u where u.username = ? AND u.password = ?";
    public static User activeUser;

    public static boolean logIn(String username,String password){
        Connection connection = DataBaseService.getConnection();
        try {
            PreparedStatement logInStatement = connection.prepareStatement(LOG_IN_QUERY);
            logInStatement.setString(1,username);
            logInStatement.setString(2,password);
            ResultSet resultSet = logInStatement.executeQuery();

            if(resultSet.next()) {
                String role = resultSet.getString("role");
                return false;
            }
            return false;
        }
        catch (SQLException e){
            return false;
        }
    }
}
