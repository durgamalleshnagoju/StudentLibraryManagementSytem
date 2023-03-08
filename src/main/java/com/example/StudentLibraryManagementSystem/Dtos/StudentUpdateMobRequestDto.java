package com.example.StudentLibraryManagementSystem.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentUpdateMobRequestDto {

    private int id;
    private String mobNo;
}
