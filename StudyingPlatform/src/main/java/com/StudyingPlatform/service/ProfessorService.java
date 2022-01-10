package com.StudyingPlatform.service;

import com.StudyingPlatform.model.Address;
import com.StudyingPlatform.model.Professor;
import com.StudyingPlatform.model.Subject;
import com.StudyingPlatform.model.User;
import com.StudyingPlatform.service.Exceptions.EmptyResultSetException;
import com.StudyingPlatform.service.Exceptions.SubjectNotFoundException;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ProfessorService {
    public static Professor mapResultSet(ResultSet resultSet) throws SQLException, EmptyResultSetException {
        if (resultSet.next()) {
            Professor professor = new Professor(
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
                            resultSet.getString("region"),
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
            resultSet.previous();
            return professor;
        } else {
            resultSet.previous();
            throw new EmptyResultSetException();
        }
    }

    public static void assignProfessorToSubject(Professor professor, Subject subject) throws SQLException {
        Connection connection = DataBaseService.getConnection();
        CallableStatement stmt = connection.prepareCall("call professor_assign_subject(?,?)");
        stmt.setInt(1, professor.getId());
        stmt.setInt(2, subject.getId());
        stmt.execute();
    }

    public static List<Subject> professorGetSubjects(Professor professor) throws SQLException, SubjectNotFoundException {
        Connection connection = DataBaseService.getConnection();
        CallableStatement stmt = connection.prepareCall("call  professor_get_subjects(?)", ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        stmt.setInt(1, professor.getId());
        ResultSet resultSet = stmt.executeQuery();
        try {
            return SubjectService.mapFullResultSet(resultSet);
        } catch (EmptyResultSetException e) {
            throw new SubjectNotFoundException();
        }
    }
}
