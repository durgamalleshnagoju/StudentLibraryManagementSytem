package com.example.StudentLibraryManagementSystem.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private int age;
    private String country;
    private double rating;


    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    List<Book> BooksWritten;


}
