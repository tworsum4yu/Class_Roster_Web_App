package com.example.class_roster.dao.implementations;

import com.example.class_roster.dao.DaoInterface;
import com.example.class_roster.dao.mappers.StudentCourseMapper;
import com.example.class_roster.dao.mappers.StudentMapper;
import com.example.class_roster.dto.Student;
import com.example.class_roster.dto.Student;
import com.example.class_roster.dto.StudentCourse;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentDaoImpl implements DaoInterface<Student> {

    private final JdbcTemplate jdbc;

    public StudentDaoImpl(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public Student addRecord(Student student) {

        final String sql = "INSERT INTO Student (firstName, lastName) VALUES (?, ?)";
        jdbc.update(sql, student.getFirstName(), student.getLastName(), student);

        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID();", Integer.class);
        student.setStudentID(newId);

        return student;
    }

    @Override
    public List<Student> getAllRecords() {
        final String sql = "SELECT * FROM student";
        return jdbc.query(sql, new StudentMapper());
    }

    @Override
    public Student getRecordByID(int studentID) {
        final String sql = "SELECT * FROM student WHERE StudentID = ?";
        return jdbc.queryForObject(sql, new StudentMapper(), studentID);
    }

    @Override
    public boolean updateRecord(Student student) {
        final String sql = "update student set firstName = ?, lastName = ? where studentID = ?";

        return jdbc.update(sql, student.getFirstName(), student.getLastName(), student.getStudentID()) > 0;
    }

    @Override
    public boolean deleteRecord(int studentID) {
        final String sql = "delete from student where studentID = ?";
        return jdbc.update(sql, studentID) > 0;
    }

    public List<StudentCourse> getStudentCourseRecordByID(int studentID) {
        final String sql = "SELECT * FROM studentcourse WHERE studentID = ?";
        return jdbc.query(sql, new StudentCourseMapper(), studentID);
    }

    public boolean deleteStudentCourseRecord(int studentID) {
        final String sql = "delete from studentcourse where studentID = ?";
        return jdbc.update(sql, studentID) > 0;
    }

}
