package com.example.class_roster.dao.implementations;

import com.example.class_roster.dao.DaoInterface;
import com.example.class_roster.dao.mappers.CourseMapper;
import com.example.class_roster.dto.Course;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CourseDaoImpl implements DaoInterface<Course> {

    private final JdbcTemplate jdbc;

    public CourseDaoImpl(JdbcTemplate jdbc)
    {
        this.jdbc = jdbc;
    }

    @Override
    public Course addRecord(Course course) {

        final String sql = "INSERT INTO course (courseName) VALUES (?)";
        jdbc.update(sql, course.getCourseName());

        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID();", Integer.class);
        course.setCourseID(newId);

        return course;
    }

    @Override
    public List<Course> getAllRecords() {
        final String sql = "SELECT * FROM course";
        return jdbc.query(sql, new CourseMapper());
    }

    @Override
    public Course getRecordByID(int courseID) {
        final String sql = "SELECT * FROM course WHERE courseID = ?";
        return jdbc.queryForObject(sql, new CourseMapper(), courseID);
    }

    @Override
    public boolean updateRecord(Course course) {
        final String sql = "update course set courseName = ? where courseID = ?";

        return jdbc.update(sql, course.getCourseName(), course.getCourseID()) > 0;
    }

    @Override
    public boolean deleteRecord(int recordID) {
        final String sql = "delete from course where courseID = ?";
        return jdbc.update(sql, recordID) > 0;
    }

}
