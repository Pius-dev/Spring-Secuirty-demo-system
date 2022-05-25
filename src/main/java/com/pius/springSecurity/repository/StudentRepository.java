package com.pius.springSecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pius.springSecurity.model.Student;



public interface StudentRepository extends JpaRepository<Student, Long> {

}
