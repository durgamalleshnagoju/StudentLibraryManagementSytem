package com.example.StudentLibraryManagementSystem.ResponceDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorResponseDto {
    private String name;
    private int age;
    private double rating;
    private String country;

    private List<BookResponseDto> booksWritten;
}
