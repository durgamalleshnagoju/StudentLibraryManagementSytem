package com.example.StudentLibraryManagementSystem.RequestDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IssueBookRequestDto {

    private int bookId;
    private int cardId;

}