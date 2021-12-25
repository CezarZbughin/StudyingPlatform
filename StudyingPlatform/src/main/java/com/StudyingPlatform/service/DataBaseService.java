package com.StudyingPlatform.service;

import com.StudyingPlatform.model.Professor;
import com.StudyingPlatform.model.Student;
import com.StudyingPlatform.model.User;

import java.sql.*;

public class DataBaseService {
    public final static String DB_USERNAME = "root";
    public final static String DB_PASSWORD = "root";
    public final static String DB_NAME = "StudyingPlatform";
    public final static String DB_CONNECTION_LINK = "jdbc:mysql://localhost:3306/";

    static Connection connection;
    static {
        try {
            connection = DriverManager.getConnection(
                    DataBaseService.DB_CONNECTION_LINK + DataBaseService.DB_NAME,
                    DataBaseService.DB_USERNAME,
                    DataBaseService.DB_PASSWORD);
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }

    public static User getUserById(int id){
        User user = null;
        try{
            CallableStatement stmt = connection.prepareCall("call get_user_by_id(?)");
            stmt.setInt(1,id);
            ResultSet resultSet = stmt.executeQuery();
            user = UserService.mapResultSet(resultSet);
        }catch(SQLException e){
            return null;
        }
        return user;
    }

    public static void insertUser(User user) throws SQLException {
        final String insertStudentQuery = "call insert_student (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        final String insertProfessorQuery = "call insert_professor (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        CallableStatement stmt;
        switch (user.getRole()) {
            case "STUDENT":
                stmt = connection.prepareCall(insertStudentQuery);
                stmt.setInt(17,((Student)user).getYear());
                stmt.setInt(18,((Student) user).getMinStudyingHours());
                break;
            case "PROFESSOR":
                stmt = connection.prepareCall(insertProfessorQuery);
                stmt.setInt(17,((Professor)user).getMinTeachingHours());
                stmt.setInt(18,((Professor)user).getMaxTeachingHours());
                stmt.setString(18,((Professor)user).getDepartment());
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + user.getRole());
        }
        stmt.setString(1,user.getUsername());
        stmt.setString(2,user.getPassword());
        stmt.setString(3,user.getCnp());
        stmt.setString(4,user.getFirstName());
        stmt.setString(5,user.getLastName());
        stmt.setString(6,user.getAddress().getCountry());
        stmt.setString(7,user.getAddress().getRegion());
        stmt.setString(8,user.getAddress().getTown());
        stmt.setString(9,user.getAddress().getStreetAddress());
        stmt.setString(10,user.getAddress().getPostalCode());
        stmt.setString(11,user.getPhone());
        stmt.setString(12,user.getEmail());
        stmt.setString(13,user.getIban());
        stmt.setString(14,user.getContractNumber());
        stmt.setBoolean(15,user.isAdmin());
        stmt.setBoolean(16,user.isSuperAdmin());
        stmt.execute();
    }

    public static Connection getConnection(){
        return connection;
    }
}
