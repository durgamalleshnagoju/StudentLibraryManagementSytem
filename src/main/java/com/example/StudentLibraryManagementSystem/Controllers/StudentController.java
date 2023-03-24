package com.example.StudentLibraryManagementSystem.Controllers;

import com.example.StudentLibraryManagementSystem.RequestDto.StudentUpdateMobRequestDto;
import com.example.StudentLibraryManagementSystem.Model.Student;
import com.example.StudentLibraryManagementSystem.Services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService studentService;
    @PostMapping("/add")
    public ResponseEntity createStudent(@RequestBody Student student){
        try{
            String result = studentService.createStudent(student);
            return new ResponseEntity<>(result,HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity("Invalid attributes", HttpStatus.BAD_REQUEST);
        }

    }

    @DeleteMapping("/deleteUserById/{StudentId}")
    public ResponseEntity deleteStudent(@PathVariable int StudentId){
        try{
            studentService.deleteStudent(StudentId);
            return new ResponseEntity<>("Student deleted Successfully !",HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>("No entity found", HttpStatus.NO_CONTENT);
        }

    }
    @GetMapping("/get_user")
    public ResponseEntity getNameByEmail(@RequestParam("email")String email){
        try{
            String result = studentService.findNameByEmail(email);
            return new ResponseEntity<>(result,HttpStatus.FOUND);
        } catch (Exception e){
            return new ResponseEntity<>("No Entity Found!",HttpStatus.NO_CONTENT);
        }
    }


    @PutMapping("/update_mob")
    public ResponseEntity updateMob(@RequestBody StudentUpdateMobRequestDto studentReqDto){
        try{
            String result = studentService.updateMobNo(studentReqDto);
            return new ResponseEntity<>(result,HttpStatus.FOUND);
        } catch (Exception e){
            return new ResponseEntity<>("Entity Not Found",HttpStatus.NOT_FOUND);
        }
    }
}
