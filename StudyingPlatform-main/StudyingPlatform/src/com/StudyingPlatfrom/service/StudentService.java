package com.StudyingPlatfrom.service;

import com.StudyingPlatfrom.model.Student;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentService extends UserService<Student> {


    @Override
    protected Student newInstance() {
        return new Student();
    }

    @Override
    public Student mapUser(ResultSet resultSet) {
        Student student = super.mapUser(resultSet);
        try {

            student.setYear(resultSet.getInt("year"));
            student.setMinStudyingHours(resultSet.getInt("minStudyingHours"));

        } catch (SQLException e) {
            System.out.println(e);
        }
        return student;
    }
}
