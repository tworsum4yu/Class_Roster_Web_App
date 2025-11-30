package com.example.class_roster.services.implementations;

import com.example.class_roster.dao.implementations.TeacherCourseDaoImpl;
Simport com.example.class_roster.dto.TeacherCourse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherCourseServiceImpl {

    private final TeacherCourseDaoImpl tcDao;

    public TeacherCourseServiceImpl(TeacherCourseDaoImpl tcDao) {
        this.tcDao = tcDao;
    }

    public List<TeacherCourse> getAllRecords() {
        return tcDao.getAllRecords();
    }

    public TeacherCourse addRecord(TeacherCourse tc) {
        return tcDao.addRecord(tc);
    }

    public ResponseEntity<Void> deleteRecord(int teacherID, int courseID) {
        if(tcDao.deleteRecord(teacherID, courseID)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
