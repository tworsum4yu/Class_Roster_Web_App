package com.example.class_roster.dao.mappers;

import com.example.class_roster.dto.TeacherCourse;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TeacherCourseMapper implements RowMapper<TeacherCourse> {
    @Override
    public TeacherCourse mapRow(ResultSet rs, int rowNum) throws SQLException {
        TeacherCourse tc = new TeacherCourse();
        tc.setTeacherID(rs.getInt("teacherID"));
        tc.setCourseID(rs.getInt("courseID"));
        tc.setSemester(rs.getString("semester"));
        return tc;
    }
}
