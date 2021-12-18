package com.StudyingPlatfrom.service;

import com.StudyingPlatfrom.model.Address;
import com.StudyingPlatfrom.model.User;
import com.StudyingPlatfrom.view.GreenView;
import com.StudyingPlatfrom.view.MainFrame;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class UserService <T extends User>{
    public static int logIn(String username, String password) {
        Connection connection = DataBaseService.getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from user where username = \"" + username + "\" AND password = \"" + password + "\"");

            if (resultSet.next()) {
                MainFrame.setView(new GreenView());
                return resultSet.getInt("id");
            }
            return -1;
        } catch (SQLException e) {
            return -1;
        }
    }

    public T mapUser(ResultSet resultSet) {
        T user = newInstance();
        try {

            user.setId(resultSet.getInt("id"));
            user.setUsername(resultSet.getString("username"));
            user.setRole(resultSet.getString("role"));
            user.setCnp(resultSet.getString("cnp"));
            user.setFirstName(resultSet.getString("firstname"));
            user.setLastName(resultSet.getString("lastname"));
            user.setAddress(resultSet.getObject("adress",Address.class));
            user.setPhone(resultSet.getString("phone"));
            user.setEmail(resultSet.getString("email"));
            user.setIban(resultSet.getString("iban"));
            user.setContractNumber(resultSet.getString("contractNumber"));
            user.setIsAdmin(resultSet.getBoolean("isAdmin"));
            user.setSuperAdmin(resultSet.getBoolean("superAdmin"));
        }catch (SQLException e){
            System.out.println(e);


        }
return user;
    }

    protected abstract T newInstance() ;



   /* public static Professor map(ResultSet resultSet){
        try{
            Professor professor= new Professor();
            professor.setId(resultSet.getInt("id"));
            return professor;
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }*/
}
