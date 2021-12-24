package com.StudyingPlatform.service;

import com.StudyingPlatform.model.Professor;
import com.StudyingPlatform.model.Student;
import com.StudyingPlatform.model.User;
import javafx.scene.control.TextField;

import java.sql.*;

public class DataBaseService {
    public final static String DB_USERNAME = "root";
    public final static String DB_PASSWORD = "alabala";
    public final static String DB_NAME = "mydb";
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
            return null;
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

    public static void insertUser(User user) throws SQLException{
        final String INSERT_USER = "insert into user(username,password,role,CNP,first_name,last_name,phone,email,iban,contract_number,is_admin,is_super_admin) VALUES " +
                "(?,?,?,?,?,?,?,?,?,?,?,?)";
        final String INSERT_STUDENT = "insert into student(id,year,min_studying_hours) VALUES (?, ?, ?)";
        final String INSERT_PROFESSOR = "insert into professor(id,min_teaching_hours,max_teaching_hours,department) VALUES (?, ?, ?, ?)";
        final String INSERT_ADDRESS = "insert into address(id,country,region,town,street_address,postal_code) VALUES (?, ?, ?, ?, ?, ?)";

        PreparedStatement statementUser = connection.prepareStatement(INSERT_USER, Statement.RETURN_GENERATED_KEYS);
        statementUser.setString(1,user.getUsername());
        statementUser.setString(2,user.getPassword());
        statementUser.setString(3,user.getRole());
        statementUser.setString(4,user.getCnp());
        statementUser.setString(5,user.getFirstName());
        statementUser.setString(6,user.getLastName());
        statementUser.setString(7,user.getPhone());
        statementUser.setString(8,user.getEmail());
        statementUser.setString(9,user.getIban());
        statementUser.setString(10,user.getContractNumber());
        statementUser.setBoolean(11,user.isAdmin());
        statementUser.setBoolean(12,user.isSuperAdmin());
        statementUser.executeUpdate();

        ResultSet generatedKeys = statementUser.getGeneratedKeys();
        if(generatedKeys.next()){
            int id = generatedKeys.getInt(1);

            PreparedStatement statementAddress = connection.prepareStatement(INSERT_ADDRESS);
            statementAddress.setInt(1,id);
            statementAddress.setString(2,user.getAddress().getCountry());
            statementAddress.setString(3,user.getAddress().getRegion());
            statementAddress.setString(4,user.getAddress().getTown());
            statementAddress.setString(5,user.getAddress().getStreetAddress());
            statementAddress.setString(6,user.getAddress().getPostalCode());
            statementAddress.executeUpdate();

            if(user instanceof Student){
                Student student = (Student) user;
                PreparedStatement statementStudent = connection.prepareStatement(INSERT_STUDENT);
                statementStudent.setInt(1,id);
                statementStudent.setInt(2,student.getYear());
                statementStudent.setInt(3,student.getMinStudyingHours());
                statementAddress.executeUpdate();
            }else{
                Professor professor = (Professor) user;
                PreparedStatement statementProfessor = connection.prepareStatement(INSERT_PROFESSOR);
                statementProfessor.setInt(1,id);
                statementProfessor.setInt(2,professor.getMinTeachingHours());
                statementProfessor.setInt(3,professor.getMaxTeachingHours());
                statementProfessor.setString(4,professor.getDepartment());
                statementProfessor.executeUpdate();
            }
        }else throw new SQLException("Could not generate an id");
    }

    public static Connection getConnection(){
        return connection;
    }
}
