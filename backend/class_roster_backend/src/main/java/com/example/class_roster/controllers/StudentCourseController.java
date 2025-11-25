package com.example.class_roster.controllers;

import com.example.class_roster.dto.StudentCourse;
import com.example.class_roster.services.implementations.StudentCourseServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/studentcourse")
@CrossOrigin
public class StudentCourseController {

    private final StudentCourseServiceImpl scService;

    public StudentCourseController(StudentCourseServiceImpl scService) {
        this.scService = scService;
    }

    @GetMapping("/pairs")
    public List<StudentCourse> getAllStudentCoursePairs() {
        return scService.getAllRecords();
    }

    @PostMapping
    public StudentCourse addStudentCoursePair(@RequestBody StudentCourse sc) {
        return scService.addRecord(sc);
    }

    @DeleteMapping("/{studentID}/{courseID}")
    public ResponseEntity<Void> deleteStudentCoursePairs(@PathVariable int studentID, @PathVariable int courseID) {
        return scService.deleteRecord(studentID, courseID);
    }

}
