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

    private final static String PROFESSOR_BY_ID_QUERY = "select al1.id, username, password, role, CNP, first_name, last_name, phone, email, iban, contract_number, is_admin, is_super_admin, min_teaching_hours, max_teaching_hours, department, country, region, town, street_address, postal_code from ((select * from user u where u.id = ?) as al1 left join professor p on p.id = ? left join address a on a.id = ?)";
    private final static String STUDENT_BY_ID_QUERY = "select al1.id, username, password, role, CNP, first_name, last_name, phone, email, iban, contract_number, is_admin, is_super_admin, year, min_studying_hours, country, region, town, street_address, postal_code from ((select * from user u where u.id=?) as al1 left join student s on s.id=? left join address a on a.id = ?)";
    private static final String ROLE_BY_ID_QUERY = "select u.role from User u where u.id = ?";
    private static final String USERID_BY_USERNAME_QUERY = "select u.id from User u where u.username = ?";

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
        try{
            PreparedStatement roleStatement = connection.prepareStatement(ROLE_BY_ID_QUERY);
            roleStatement.setInt(1,id);
            ResultSet roleRS = roleStatement.executeQuery();
            if(roleRS.next()){
               String role = roleRS.getString("role");
               PreparedStatement userStatement;
               if(role.equals("STUDENT")){
                   userStatement = connection.prepareStatement(STUDENT_BY_ID_QUERY);
               }else{
                   userStatement = connection.prepareStatement(PROFESSOR_BY_ID_QUERY);
               }
               userStatement.setInt(1,id);
               userStatement.setInt(2,id);
               userStatement.setInt(3,id);
               ResultSet userRS = userStatement.executeQuery();
                if(role.equals("STUDENT")){
                    return StudentService.mapResultSet(userRS);
                }else{
                    return ProfessorService.mapResultSet(userRS);
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public static User getUserByUsername(String username){
        try{
            PreparedStatement statement = connection.prepareStatement(USERID_BY_USERNAME_QUERY);
            statement.setString(1,username);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                int id = resultSet.getInt("id");
                return getUserById(id);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public static Connection getConnection(){
        return connection;
    }
}
