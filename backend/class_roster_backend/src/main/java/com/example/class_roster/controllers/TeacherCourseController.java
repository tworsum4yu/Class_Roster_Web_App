package com.example.class_roster.controllers;

import com.example.class_roster.dto.TeacherCourse;
import com.example.class_roster.services.implementations.TeacherCourseServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teachercourse")
@CrossOrigin
public class TeacherCourseController {

    private final TeacherCourseServiceImpl tcService;

    public TeacherCourseController(TeacherCourseServiceImpl tcService) {
        this.tcService = tcService;
    }

    @GetMapping("/pairs")
    public List<TeacherCourse> getAllTeacherCoursePairs() {
        return tcService.getAllRecords();
    }

    @PostMapping
    public TeacherCourse addTeacherCoursePair(@RequestBody TeacherCourse tc) {
        return tcService.addRecord(tc);
    }

    @DeleteMapping("/{teacherID}/{courseID}")
    public ResponseEntity<Void> deleteTeacherCoursePairs(@PathVariable int teacherID, @PathVariable int courseID) {
        return tcService.deleteRecord(teacherID, courseID);
    }

}
