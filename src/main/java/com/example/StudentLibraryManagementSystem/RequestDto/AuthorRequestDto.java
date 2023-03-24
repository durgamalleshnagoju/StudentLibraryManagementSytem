package com.example.StudentLibraryManagementSystem.RequestDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorRequestDto {
    private String name;

    private int age;

    private String country;

    private double rating;
}
