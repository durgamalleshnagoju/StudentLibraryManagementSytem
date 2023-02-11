package com.example.StudentLibraryManagementSystem.Services;

import com.example.StudentLibraryManagementSystem.Model.Author;
import com.example.StudentLibraryManagementSystem.Model.Book;
import com.example.StudentLibraryManagementSystem.Repositories.AuthorRepository;
import com.example.StudentLibraryManagementSystem.Repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorRepository authorRepository;



    public String addBook(Book book){

        int authorId = book.getAuthor().getId();

        Author author;
        try{
            author = authorRepository.findById(authorId).get();
        }
        catch (NoSuchElementException e){
            return "Author unidentified !";
        }

        List<Book> booksList = author.getBooksWritten();

        booksList.add(book);

        author.setBooksWritten(booksList);

        authorRepository.save(author);


        return "Book added Successfully";
    }
}
