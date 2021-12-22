package com.StudyingPlatform.service;

import com.StudyingPlatform.model.Address;
import com.StudyingPlatform.model.Student;
import com.StudyingPlatform.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentService extends UserService{
    public static Student mapResultSet(ResultSet resultSet) {
        try {
            if (resultSet.next()){
                return new Student(
                        resultSet.getInt("id"),
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getString("role"),
                        resultSet.getString("CNP"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        new Address(
                                resultSet.getString("country"),
                                resultSet.getString("town"),
                                resultSet.getString("street_address"),
                                resultSet.getString("postal_code")
                        ),
                        resultSet.getString("phone"),
                        resultSet.getString("email"),
                        resultSet.getString("iban"),
                        resultSet.getString("contract_number"),
                        resultSet.getBoolean("is_admin"),
                        resultSet.getBoolean("is_super_admin"),
                        resultSet.getInt("year"),
                        resultSet.getInt("min_studying_hours")
                );
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
