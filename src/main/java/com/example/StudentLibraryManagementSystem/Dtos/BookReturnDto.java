package com.example.StudentLibraryManagementSystem.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookReturnDto {

    private int bookId;
    private int cardId;

}
