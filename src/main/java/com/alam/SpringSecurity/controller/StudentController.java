package com.alam.SpringSecurity.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.alam.SpringSecurity.bean.Student;
import com.alam.SpringSecurity.service.StudentService;


@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/addStudents")
    public void addStudents(@RequestBody List<Student> students) {
        studentService.addStudents(students);
    }
}
