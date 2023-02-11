package com.example.StudentLibraryManagementSystem.Services;

import com.example.StudentLibraryManagementSystem.Model.Author;
import com.example.StudentLibraryManagementSystem.Repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {

    @Autowired
    AuthorRepository authorRepository;

    public String createAuthor(Author author){
        authorRepository.save(author);
        return "Author created Successfully";
    }
}
