package com.example.class_roster.controllers;

import com.example.class_roster.dto.Teacher;
import com.example.class_roster.dto.Teacher;
import com.example.class_roster.services.implementations.TeacherServiceImpl;
import com.example.class_roster.services.implementations.TeacherServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teacher")
@CrossOrigin
public class TeacherController {

    private final TeacherServiceImpl teacherService;

    public TeacherController(TeacherServiceImpl teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping("/teachers")
    public List<Teacher> getAllTeachers() {
        return teacherService.getAllRecords();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Teacher> getTeacherById(@PathVariable int id) {
        return teacherService.getRecord(id);
    }

    @PostMapping
    public Teacher createTeacher(@RequestBody Teacher teacher) {
        return teacherService.addRecord(teacher);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateTeacher(@PathVariable int id, @RequestBody Teacher teacher) {
        return teacherService.updateRecord(id, teacher);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeacher(@PathVariable int id) {
        return teacherService.deleteRecord(id);
    }

}
