package com.example.class_roster.services.implementations;

import com.example.class_roster.dao.implementations.StudentDaoImpl;
import com.example.class_roster.dto.Student;
import com.example.class_roster.services.ServiceInterface;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements ServiceInterface<Student> {
    private final StudentDaoImpl studentDao;

    public StudentServiceImpl(StudentDaoImpl studentDao) {
        this.studentDao = studentDao;
    }

    @Override
    public List<Student> getAllRecords() {
        return studentDao.getAllRecords();
    }

    // Try catches for all methods below
    // They can and will fail
    @Override
    public ResponseEntity<Student> getRecord(int studentID) {
        Student result = studentDao.getRecordByID(studentID);
        if(result == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(result);
    }

    @Override
    public Student addRecord(Student student) {
        return studentDao.addRecord(student);
    }

    @Override
    public ResponseEntity<Void> updateRecord(int id, Student student) {

        ResponseEntity response = ResponseEntity.status(HttpStatus.NOT_FOUND).body("");

        if(id != student.getStudentID()){
            response = ResponseEntity.status(HttpStatus.UNPROCESSABLE_CONTENT).body("IDs do not match");
        }
        else if(studentDao.updateRecord(student)) {
            response = ResponseEntity.status(HttpStatus.OK).body("Todo was updated");
        }

        return response;
    }

    @Override
    public ResponseEntity<Void> deleteRecord(int studentID) {
        if(studentDao.deleteRecord(studentID)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
