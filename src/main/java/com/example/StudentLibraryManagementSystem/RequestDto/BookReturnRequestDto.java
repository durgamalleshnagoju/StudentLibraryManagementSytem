package com.example.StudentLibraryManagementSystem.RequestDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookReturnRequestDto {

    private int bookId;
    private int cardId;

}