package com.pius.springSecurity.controller;

import com.pius.springSecurity.exceptions.ResourceNotFoundException;
import com.pius.springSecurity.model.Student;
import com.pius.springSecurity.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("management/api/v1/students")
public class StudentManagementController {

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

    @PutMapping("/update/{id}")
    public void  updateStudent (@PathVariable Long student_Id, @RequestBody Student student) {
        System.out.println("Updating");

    }

    @DeleteMapping("/delete/{id}")
    public void deleteStudent (@RequestParam Long student_Id ) {

        System.out.println("deleting");

    }

}
