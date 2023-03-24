package com.example.StudentLibraryManagementSystem.Controllers;

import com.example.StudentLibraryManagementSystem.RequestDto.AuthorRequestDto;
import com.example.StudentLibraryManagementSystem.ResponceDto.AuthorResponseDto;
import com.example.StudentLibraryManagementSystem.Services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    AuthorService authorService;

    @PostMapping("/add")
    public ResponseEntity addAuthor(@RequestBody AuthorRequestDto authorRequestDto){
        try{
            String response = authorService.createAuthor(authorRequestDto);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/getAuthor")
    public ResponseEntity getAuthor(@RequestParam("authorId")Integer authorId){
        try{
            AuthorResponseDto response = authorService.getAuthor(authorId);
            return new ResponseEntity<>(response, HttpStatus.FOUND);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
