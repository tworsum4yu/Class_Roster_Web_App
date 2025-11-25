package com.example.class_roster.dao.implementations;

import com.example.class_roster.dao.DaoInterface;
import com.example.class_roster.dao.mappers.TeacherCourseMapper;
import com.example.class_roster.dao.mappers.TeacherMapper;
import com.example.class_roster.dto.Teacher;
import com.example.class_roster.dto.TeacherCourse;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TeacherDaoImpl implements DaoInterface<Teacher> {

    private final JdbcTemplate jdbc;

    public TeacherDaoImpl(JdbcTemplate jdbc)
    {
        this.jdbc = jdbc;
    }

    @Override
    public Teacher addRecord(Teacher teacher) {

        final String sql = "INSERT INTO teacher (firstName, lastName, title, office, department) VALUES (?, ?, ?, ?, ?)";
        jdbc.update(sql, teacher.getFirstName(), teacher.getLastName(), teacher.getTitle(), teacher.getOffice(), teacher);

        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID();", Integer.class);
        teacher.setTeacherID(newId);

        return teacher;
    }

    @Override
    public List<Teacher> getAllRecords() {
        final String sql = "SELECT * FROM teacher";
        return jdbc.query(sql, new TeacherMapper());
    }

    @Override
    public Teacher getRecordByID(int teacherID) {
        final String sql = "SELECT * FROM teacher WHERE teacherID = ?";
        return jdbc.queryForObject(sql, new TeacherMapper(), teacherID);
    }

    @Override
    public boolean updateRecord(Teacher teacher) {
        final String sql = "update teacher set firstName = ?, lastName = ?, title = ?, office = ?, department = ?, where courseID = ?";

        return jdbc.update(sql, teacher.getFirstName(), teacher.getLastName(), teacher.getTitle(), teacher.getOffice(), teacher.getDepartment(), teacher.getTeacherID()) > 0;
    }

    @Override
    public boolean deleteRecord(int teacherID) {
        final String sql = "delete from teacher where teacherID = ?";
        return jdbc.update(sql, teacherID) > 0;
    }

    public List<TeacherCourse> getAllTeacherCourseRecords() {
        final String sql = "SELECT * FROM teachercourse";
        return jdbc.query(sql, new TeacherCourseMapper());
    }

    public TeacherCourse getTeacherCourseRecordByID(int teacherID) {
        final String sql = "SELECT * FROM teachercourse WHERE teacherID = ?";
        return jdbc.queryForObject(sql, new TeacherCourseMapper(), teacherID);
    }


}
