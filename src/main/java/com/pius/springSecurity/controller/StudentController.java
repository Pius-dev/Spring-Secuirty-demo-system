package com.pius.springSecurity.controller;

import java.util.List;

import javax.validation.Valid;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pius.springSecurity.exceptions.ResourceNotFoundException;
import com.pius.springSecurity.model.Student;
import com.pius.springSecurity.repository.StudentRepository;

@RestController
@RequestMapping("api/v1/students")

public class StudentController {
	
	@Autowired
    StudentRepository studentRepository;

	// Get a Single 

    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable(value = "studentId") Long student_Id) {
        return studentRepository.findById(student_Id)
                .orElseThrow(() -> new ResourceNotFoundException("Student", "studentId", student_Id));
    }
	
    @GetMapping("/all")
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }
    
    // Create a new 

    @PostMapping("/add")
    public Student createStudent(@Valid @RequestBody Student student) {
        return studentRepository.save(student);
    }
}
