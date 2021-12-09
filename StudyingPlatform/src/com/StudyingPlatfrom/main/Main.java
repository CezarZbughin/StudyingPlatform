package com.StudyingPlatfrom.main;

import com.StudyingPlatfrom.service.DataBaseService;

import java.sql.*;

public class Main {
    public static void main(String[] args) {
        Connection connection = DataBaseService.getConnection();
    }
}