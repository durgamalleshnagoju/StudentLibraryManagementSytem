package com.example.StudentLibraryManagementSystem.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @Column(unique = true)
    private String email;
    private String mobileNumber;

    private int age;
    private String country;

    @OneToOne(mappedBy = "StudentVariableName", cascade = CascadeType.ALL)
    private Card card;

}
