package com.example.StudentLibraryManagementSystem.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorEntryDto {
    private String name;

    private int age;

    private String country;

    private double rating;
}
