package com.example.class_roster.dao.mappers;

import com.example.class_roster.dto.Course;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CourseMapper implements RowMapper<Course> {
    @Override
    public Course mapRow(ResultSet rs, int rowNum) throws SQLException {
        Course course = new Course();
        course.setCourseID(rs.getInt("courseID"));
        course.setCourseName(rs.getString("courseName"));
        return course;
    }
}
