package com.alam.SpringSecurity.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.alam.SpringSecurity.bean.Student;
import com.alam.SpringSecurity.repository.StudentRepo;

@Service
public class StudentService {
    
    private final StudentRepo studentRepo;
    public StudentService(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
    }

    public void addStudents(List<Student> students) {
        studentRepo.saveAll(students);
    }

}
