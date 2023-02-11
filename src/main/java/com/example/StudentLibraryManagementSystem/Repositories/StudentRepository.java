package com.example.StudentLibraryManagementSystem.Repositories;

import com.example.StudentLibraryManagementSystem.Model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {

}
