package com.example.class_roster.services.implementations;

import com.example.class_roster.dao.implementations.StudentCourseDaoImpl;
import com.example.class_roster.dto.StudentCourse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentCourseServiceImpl {

    private final StudentCourseDaoImpl scDao;

    public StudentCourseServiceImpl(StudentCourseDaoImpl scDao) {
        this.scDao = scDao;
    }

    public List<StudentCourse> getAllRecords() {
        return scDao.getAllRecords();
    }

    public StudentCourse addRecord(StudentCourse sc) {
        return scDao.addRecord(sc);
    }

    public ResponseEntity<Void> deleteRecord(int studentID, int courseID) {
        if(scDao.deleteRecord(studentID, courseID)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
