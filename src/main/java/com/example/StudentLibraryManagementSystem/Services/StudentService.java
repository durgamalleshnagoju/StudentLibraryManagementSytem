package com.example.StudentLibraryManagementSystem.Services;

import com.example.StudentLibraryManagementSystem.Dtos.StudentUpdateMobRequestDto;
import com.example.StudentLibraryManagementSystem.Enums.CardStatus;
import com.example.StudentLibraryManagementSystem.Model.Card;
import com.example.StudentLibraryManagementSystem.Model.Student;
import com.example.StudentLibraryManagementSystem.Repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;
    public String createStudent(Student student){

        // first giving attributes to card

        Card card = new Card();
        card.setCardStatus(CardStatus.ACTIVATED);
        card.setStudentVariableName(student);

        // setting student attributes
        student.setCard(card);

        // saving student , card will be saved automatically by cascade effect
        studentRepository.save(student);
        return "Student and card created successfully";
    }

    public void deleteStudent(int studentId) {
        studentRepository.deleteById(studentId);
    }
    public String findNameByEmail(String email){

        Student student = studentRepository.findByEmail(email);

        return student.getName();
    }


    public String updateMobNo(StudentUpdateMobRequestDto studentReq){


        Student originalStudent = studentRepository.findById(studentReq.getId()).get();


        originalStudent.setMobileNumber(studentReq.getMobNo());

        studentRepository.save(originalStudent);

        return "Student has been updated successfully. ";

    }
}
