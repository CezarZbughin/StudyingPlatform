package com.StudyingPlatform.service;

import com.StudyingPlatform.model.Professor;
import com.StudyingPlatform.model.Student;
import com.StudyingPlatform.model.Subject;
import com.StudyingPlatform.model.User;
import com.StudyingPlatform.service.Exceptions.EmptyResultSetException;
import com.StudyingPlatform.service.Exceptions.SubjectNotFoundException;
import com.StudyingPlatform.service.Exceptions.UserNotFoundException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataBaseService {
    //
    //CONNECTION
    //
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
    public static Connection getConnection(){
        return connection;
    }
    //
    // USERS
    //
    public static User getUserById(int id) throws UserNotFoundException {
        try{
            CallableStatement stmt = connection.prepareCall("call get_user_by_id(?)", ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            stmt.setInt(1,id);
            ResultSet resultSet = stmt.executeQuery();
            return UserService.mapResultSet(resultSet);
        }catch(SQLException e){
            throw new UserNotFoundException("something went wrong");
        }catch (EmptyResultSetException e){
            throw new UserNotFoundException("user id not found");
        }
    }

    public static List<User> getUsersByName(String firstName,String lastName) throws SQLException,UserNotFoundException{
        CallableStatement stmt = connection.prepareCall("call get_user_id_by_name(?,?)", ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        stmt.setString(1,firstName);
        stmt.setString(2,lastName);
        ResultSet resultSet = stmt.executeQuery();
        List<Integer> idList = new ArrayList<>();
        while(resultSet.next()) {
            idList.add(resultSet.getInt("id"));
        }
        return usersByIdList(idList);
    }

    public static List<User> getAllUsers() throws SQLException, UserNotFoundException{
        List<Integer> idList = new ArrayList<>();
        CallableStatement idsStmt = connection.prepareCall("call get_all_users_id()");
        ResultSet resultSet = idsStmt.executeQuery();
        while(resultSet.next()){
            idList.add(resultSet.getInt("id"));
        }
        return usersByIdList(idList);
    }

    public static List<User> getAllStudents() throws SQLException, UserNotFoundException {
        List<Integer> idList = new ArrayList<>();
        CallableStatement idsStmt = connection.prepareCall("call get_all_students_id()");
        ResultSet resultSet = idsStmt.executeQuery();
        while(resultSet.next()){
            idList.add(resultSet.getInt("id"));
        }
        return usersByIdList(idList);
    }

    public static List<User> getAllProfessor() throws SQLException, UserNotFoundException {
        List<Integer> idList = new ArrayList<>();
        CallableStatement idsStmt = connection.prepareCall("call get_all_professors_id()");
        ResultSet resultSet = idsStmt.executeQuery();
        while(resultSet.next()){
            idList.add(resultSet.getInt("id"));
        }
        return usersByIdList(idList);
    }

    private static List<User> usersByIdList(List<Integer> idList) throws UserNotFoundException{
        List<User> users = new ArrayList<>();
        for(int id:idList) {
            try {
                users.add(DataBaseService.getUserById(id));
            } catch (UserNotFoundException e) {
                continue;
            }
        }
        if(users.isEmpty()){
            throw new UserNotFoundException("no user was found");
        }
        return users;
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
                stmt.setString(19,((Professor)user).getDepartment());
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

    public static void updateUser(User user)throws SQLException{
        String queryStudent = "call update_student(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        String queryProfessor = "call update_professor(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        CallableStatement stmt;
        if("STUDENT".equals(user.getRole())){
            stmt = connection.prepareCall(queryStudent);
            stmt.setInt(17, ((Student)user).getMinStudyingHours());
            stmt.setInt(18, ((Student)user).getYear());
        }else if("PROFESSOR".equals(user.getRole())){
            stmt = connection.prepareCall(queryProfessor);
            stmt.setInt(17, ((Professor)user).getMinTeachingHours());
            stmt.setInt(18, ((Professor)user).getMaxTeachingHours());
            stmt.setString(19, ((Professor)user).getDepartment());
        }else{
            throw new IllegalStateException("corrrupt user");
        }
        stmt.setInt(1,user.getId());
        stmt.setString(2,user.getRole());
        stmt.setString(3, user.getCnp());
        stmt.setString(4, user.getFirstName());
        stmt.setString(5, user.getLastName());
        stmt.setString(6, user.getPhone());
        stmt.setString(7, user.getEmail());
        stmt.setString(8, user.getIban());
        stmt.setString(9, user.getContractNumber());
        stmt.setBoolean(10, user.isAdmin());
        stmt.setBoolean(11, user.isSuperAdmin());
        stmt.setString(12, user.getAddress().getCountry());
        stmt.setString(13, user.getAddress().getRegion());
        stmt.setString(14, user.getAddress().getTown());
        stmt.setString(15, user.getAddress().getStreetAddress());
        stmt.setString(16, user.getAddress().getPostalCode());
        stmt.execute();
    }
    //
    // SUBJECTS
    //
    public static List<Subject> getAllSubjects() throws SQLException, SubjectNotFoundException {
        CallableStatement stmt = connection.prepareCall("call get_all_subjects()", ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = stmt.executeQuery();
        List<Subject> subjectList;
        try {
            subjectList = SubjectService.mapFullResultSet(resultSet);
        }catch (EmptyResultSetException e){
            throw new SubjectNotFoundException();
        }
        return subjectList;
    }

    public static List<Subject> getSubjectsByName(String name) throws SQLException,SubjectNotFoundException{
        CallableStatement stmt = connection.prepareCall("call get_subject_by_name(?)", ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        stmt.setString(1,name);
        ResultSet resultSet = stmt.executeQuery();
        try {
            return SubjectService.mapFullResultSet(resultSet);
        }catch (EmptyResultSetException e){
            throw new SubjectNotFoundException();
        }
    }

    public static void insertSubject(Subject subject) throws SQLException{
        String insertSubjectQuery = "call insert_subject(?,?,?,?,?,?,?)";
        CallableStatement stmt = connection.prepareCall(insertSubjectQuery);
        stmt.setString(1,subject.getName());
        stmt.setString(2,subject.getDescription());
        stmt.setBoolean(3,subject.getHasLecture());
        stmt.setBoolean(4,subject.getHasSeminar());
        stmt.setBoolean(5,subject.getHasLab());
        stmt.setDate(6,subject.getDateStart());
        stmt.setDate(7,subject.getDateEnd());
        stmt.execute();
    }

    public static void updateSubject(Subject subject)throws SQLException{
        String querySubject = "call update_subject(?,?,?,?,?,?,?,?)";
        CallableStatement stmt =  connection.prepareCall(querySubject);
        stmt.setInt(1, subject.getId());
        stmt.setString(2,subject.getName());
        stmt.setString(3, subject.getDescription());
        stmt.setBoolean(4, subject.getHasLecture());
        stmt.setBoolean(5, subject.getHasSeminar());
        stmt.setBoolean(6, subject.getHasLab());
        stmt.setDate(7, subject.getDateStart());
        stmt.setDate(8, subject.getDateEnd());
        stmt.execute();
    }
}
