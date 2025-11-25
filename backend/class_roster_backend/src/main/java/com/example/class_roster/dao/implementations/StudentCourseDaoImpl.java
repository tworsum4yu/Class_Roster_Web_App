package com.example.class_roster.dao.implementations;

import com.example.class_roster.dao.DaoInterface;
import com.example.class_roster.dao.mappers.StudentCourseMapper;
import com.example.class_roster.dao.mappers.TeacherCourseMapper;
import com.example.class_roster.dto.StudentCourse;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentCourseDaoImpl {

    private final JdbcTemplate jdbc;

    public StudentCourseDaoImpl(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public StudentCourse addRecord(StudentCourse sc) {
        final String sql = "INSERT INTO studentcourse (studentID, courseID, semester, grade) VALUES (?, ?, ?, ?)";
        jdbc.update(sql, sc.getStudentID(), sc.getCourseID(), sc.getSemester(), sc.getGrade());

        return sc;
    }

    public List<StudentCourse> getAllRecords() {
        final String sql = "SELECT * FROM studentcourse";
        return jdbc.query(sql, new StudentCourseMapper());
    }

    public boolean deleteRecord(int studentID, int courseID) {
        final String sql = "delete from studentcourse where studentID = ? AND  courseID = ?";

        return jdbc.update(sql, studentID, courseID) > 0;
    }
}
