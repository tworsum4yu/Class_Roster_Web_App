package com.example.class_roster.dao.mappers;

import com.example.class_roster.dto.StudentCourse;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentCourseMapper implements RowMapper<StudentCourse> {
    @Override
    public StudentCourse mapRow(ResultSet rs, int rowNum) throws SQLException {
        StudentCourse sc = new StudentCourse();
        sc.setStudentID(rs.getInt("studentID"));
        sc.setCourseID(rs.getInt("courseID"));
        sc.setGrade(rs.getString("grade"));
        sc.setSemester(rs.getString("semester"));
        return sc;
    }
}
