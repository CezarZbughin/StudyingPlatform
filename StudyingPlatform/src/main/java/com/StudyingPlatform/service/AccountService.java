package com.StudyingPlatform.service;

import com.StudyingPlatform.model.User;

import javax.security.auth.login.LoginException;
import java.sql.*;

public class AccountService {
    public static User logIn(String username,String password) throws LoginException{
        User user = DataBaseService.getUserByUsername(username);
        if(user == null){
            throw new LoginException("username not found");
        }
        if(!user.getPassword().equals(password)){
            throw new LoginException("wrong password");
        }
        return user;
    }

}
