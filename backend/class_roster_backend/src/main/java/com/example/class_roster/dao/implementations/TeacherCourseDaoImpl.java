package com.example.class_roster.dao.implementations;

import com.example.class_roster.dao.DaoInterface;
import com.example.class_roster.dao.mappers.CourseMapper;
import com.example.class_roster.dao.mappers.TeacherCourseMapper;
import com.example.class_roster.dto.Course;
import com.example.class_roster.dto.Teacher;
import com.example.class_roster.dto.TeacherCourse;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TeacherCourseDaoImpl {

    private final JdbcTemplate jdbc;

    public TeacherCourseDaoImpl(JdbcTemplate jdbc)
    {
        this.jdbc = jdbc;
    }

    public TeacherCourse addRecord(TeacherCourse tc) {

        final String sql = "INSERT INTO teachercourse (teacherID, courseID, semester) VALUES (?, ?, ?)";
        jdbc.update(sql, tc.getTeacherID(), tc.getCourseID(), tc.getSemester());

        return tc;
    }

    public List<TeacherCourse> getAllRecords() {
        final String sql = "SELECT * FROM teachercourse";
        return jdbc.query(sql, new TeacherCourseMapper());
    }

    public boolean deleteRecord(int teacherID, int courseID) {
        final String sql = "delete from teachercourse where teacherID = ? AND  courseID = ?";

        return jdbc.update(sql, teacherID, courseID) > 0;
    }
}
