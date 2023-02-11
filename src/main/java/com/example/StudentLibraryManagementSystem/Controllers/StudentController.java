package com.example.StudentLibraryManagementSystem.Controllers;

import com.example.StudentLibraryManagementSystem.Model.Student;
import com.example.StudentLibraryManagementSystem.Services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService studentService;
    @PostMapping("/add")
    public String createStudent(@RequestBody Student student){
        return studentService.createStudent(student);
    }
}
