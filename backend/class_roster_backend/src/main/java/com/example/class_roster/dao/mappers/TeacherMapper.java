package com.example.class_roster.dao.mappers;

import com.example.class_roster.dto.Teacher;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TeacherMapper implements RowMapper<Teacher> {
    @Override
    public Teacher mapRow(ResultSet rs, int rowNum) throws SQLException {
        Teacher teacher = new Teacher();
        teacher.setTeacherID(rs.getInt("teacherID"));
        teacher.setFirstName(rs.getString("firstName"));
        teacher.setLastName(rs.getString("lastName"));
        teacher.setTitle(rs.getString("title"));
        teacher.setOffice(rs.getString("office"));
        teacher.setDepartment(rs.getString("department"));
        return teacher;
    }
}
