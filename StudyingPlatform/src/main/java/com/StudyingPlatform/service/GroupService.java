package com.StudyingPlatform.service;

import com.StudyingPlatform.model.Address;
import com.StudyingPlatform.model.Group;
import com.StudyingPlatform.model.Student;
import com.StudyingPlatform.service.Exceptions.EmptyResultSetException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GroupService {
    public static List<Group> mapResultSet(ResultSet resultSet) throws SQLException, EmptyResultSetException {
        List<Group> groups = new ArrayList<>();
        while (resultSet.next()) {
            Group studentGroup = new Group(
                    resultSet.getInt("idgrup"),
                    resultSet.getString("nume"
                    ));
            groups.add(studentGroup);
        }
        return groups;
    }
}
