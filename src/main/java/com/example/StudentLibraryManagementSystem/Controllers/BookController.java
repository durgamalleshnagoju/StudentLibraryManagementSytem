package com.example.StudentLibraryManagementSystem.Controllers;

import com.example.StudentLibraryManagementSystem.RequestDto.BookRequestDto;
import com.example.StudentLibraryManagementSystem.Services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    BookService bookService;



    @PostMapping("/add")
    public ResponseEntity addBook(@RequestBody BookRequestDto bookRequestDto){
        try{
            String result =  bookService.addBook(bookRequestDto);
            return new ResponseEntity(result, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>("Exception in adding the book",HttpStatus.BAD_REQUEST);
        }

    }
    @PutMapping("/changeBookStatus/{id}")
    public String changeBookStatus(@PathVariable("id") int bookId){
        return bookService.changeStatus(bookId);
    }

}
