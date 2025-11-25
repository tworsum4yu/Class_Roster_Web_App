package com.example.class_roster.services.implementations;

import com.example.class_roster.dao.implementations.CourseDaoImpl;
import com.example.class_roster.dto.Course;
import com.example.class_roster.services.ServiceInterface;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CourseServiceImpl implements ServiceInterface<Course> {

    private final CourseDaoImpl courseDao;

    public CourseServiceImpl(CourseDaoImpl courseDao) {
        this.courseDao = courseDao;
    }

    @Override
    public List<Course> getAllRecords() {
        return courseDao.getAllRecords();
    }

    // Try catches for all methods below
    // They can and will fail
    @Override
    public ResponseEntity<Course> getRecord(int courseID) {
        Course result = courseDao.getRecordByID(courseID);
        if(result == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(result);
    }

    @Override
    public Course addRecord(Course course) {
        return courseDao.addRecord(course);
    }

    @Override
    public ResponseEntity<Void> updateRecord(int id, Course course) {

        ResponseEntity response = ResponseEntity.status(HttpStatus.NOT_FOUND).body("");

        if(id != course.getCourseID()){
            response = ResponseEntity.status(HttpStatus.UNPROCESSABLE_CONTENT).body("IDs do not match");
        }
        else if(courseDao.updateRecord(course)) {
            response = ResponseEntity.status(HttpStatus.OK).body("Todo was updated");
        }

        return response;
    }

    @Override
    public ResponseEntity<Void> deleteRecord(int courseID) {
        if(courseDao.deleteRecord(courseID)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
