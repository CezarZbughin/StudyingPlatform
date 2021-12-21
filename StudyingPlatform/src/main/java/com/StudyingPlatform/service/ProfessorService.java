package com.StudyingPlatform.service;

import com.StudyingPlatform.model.Address;
import com.StudyingPlatform.model.Professor;
import com.StudyingPlatform.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProfessorService extends UserService{
    public static Professor mapResultSet(ResultSet resultSet) {
        try {
            if (resultSet.next()){
                return new Professor(
                        resultSet.getInt("id"),
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getString("role"),
                        resultSet.getString("CNP"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        new Address(
                                resultSet.getString("country"),
                                resultSet.getString("region"),
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
                        resultSet.getInt("min_teaching_hours"),
                        resultSet.getInt("max_teaching_hours"),
                        resultSet.getString("department")
                );
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
