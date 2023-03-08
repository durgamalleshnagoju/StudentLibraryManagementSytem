package com.example.StudentLibraryManagementSystem.Controllers;

import com.example.StudentLibraryManagementSystem.Dtos.BookRequestDto;
import com.example.StudentLibraryManagementSystem.Dtos.StudentUpdateMobRequestDto;
import com.example.StudentLibraryManagementSystem.Model.Book;
import com.example.StudentLibraryManagementSystem.Services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    BookService bookService;



    @PostMapping("/add")
    public String addBook(@RequestBody BookRequestDto bookRequestDto){
        try{
            return bookService.addBook(bookRequestDto);
        }catch (Exception e){
            return "Exception in adding the book";
        }

    }
    @PutMapping("/changeBookStatus/{id}")
    public String changeBookStatus(@PathVariable("id") int bookId){
        return bookService.changeStatus(bookId);
    }

}
