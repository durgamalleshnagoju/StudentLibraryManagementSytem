package com.example.StudentLibraryManagementSystem.Dtos;

import com.example.StudentLibraryManagementSystem.Enums.Genre;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookResponseDto {
    private String name;
    private int pages;

    private Genre genre;
}
