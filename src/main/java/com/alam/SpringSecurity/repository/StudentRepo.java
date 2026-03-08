package com.alam.SpringSecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alam.SpringSecurity.bean.Student;

@Repository
public interface StudentRepo extends JpaRepository<Student, Integer> {
    
}
