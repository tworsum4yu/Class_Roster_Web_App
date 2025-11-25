package com.example.class_roster.services.implementations;

import com.example.class_roster.dao.implementations.TeacherDaoImpl;
import com.example.class_roster.dto.Teacher;
import com.example.class_roster.services.ServiceInterface;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TeacherServiceImpl implements ServiceInterface<Teacher> {

    private final TeacherDaoImpl teacherDao;

    public TeacherServiceImpl(TeacherDaoImpl teacherDao) {
        this.teacherDao = teacherDao;
    }

    @Override
    public List<Teacher> getAllRecords() {
        return teacherDao.getAllRecords();
    }

    @Override
    public ResponseEntity<Teacher> getRecord(int teacherId) {
        Teacher result = teacherDao.getRecordByID(teacherId);
        if(result == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(result);
    }

    @Override
    public Teacher addRecord(Teacher teacher) {
        return teacherDao.addRecord(teacher);
    }

    @Override
    public ResponseEntity<Void> updateRecord(int id, Teacher teacher) {

        ResponseEntity response = ResponseEntity.status(HttpStatus.NOT_FOUND).body("");

        if(id != teacher.getTeacherID()){
            response = ResponseEntity.status(HttpStatus.UNPROCESSABLE_CONTENT).body("IDs do not match");
        }
        else if(teacherDao.updateRecord(teacher)) {
            response = ResponseEntity.status(HttpStatus.OK).body("Todo was updated");
        }

        return response;
    }

    @Override
    public ResponseEntity<Void> deleteRecord(int teacherID) {
        if(teacherDao.deleteRecord(teacherID)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
