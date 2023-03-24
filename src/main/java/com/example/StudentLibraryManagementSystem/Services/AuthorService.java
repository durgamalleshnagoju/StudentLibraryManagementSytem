package com.example.StudentLibraryManagementSystem.Services;

import com.example.StudentLibraryManagementSystem.RequestDto.AuthorRequestDto;
import com.example.StudentLibraryManagementSystem.ResponceDto.AuthorResponseDto;
import com.example.StudentLibraryManagementSystem.ResponceDto.BookResponseDto;
import com.example.StudentLibraryManagementSystem.Model.Author;
import com.example.StudentLibraryManagementSystem.Model.Book;
import com.example.StudentLibraryManagementSystem.Repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorService {

    @Autowired
    AuthorRepository authorRepository;

    public String createAuthor(AuthorRequestDto authorRequestDto)throws Exception{
        Author author = new Author();

        author.setName(authorRequestDto.getName());
        author.setAge(authorRequestDto.getAge());
        author.setCountry(authorRequestDto.getCountry());
        author.setRating(authorRequestDto.getRating());

        authorRepository.save(author);
        return "Author created Successfully";
    }

    public AuthorResponseDto getAuthor(Integer authorId)throws Exception{


        Author author =  authorRepository.findById(authorId).get();
        AuthorResponseDto authorResponseDto = new AuthorResponseDto();
        //Set its attributes.
        //List<Book> --> List<BookResponseDto>
        List<Book> bookList = author.getBooksWritten();

        List<BookResponseDto> booksWrittenDto = new ArrayList<>();

        for(Book b : bookList){

            BookResponseDto bookResponseDto = new BookResponseDto();
            bookResponseDto.setGenre(b.getGenre());
            bookResponseDto.setPages(b.getPages());
            bookResponseDto.setName(b.getName());

            booksWrittenDto.add(bookResponseDto);
        }
        //Set attributes for authorResponse Dto
        authorResponseDto.setBooksWritten(booksWrittenDto);
        authorResponseDto.setName(author.getName());
        authorResponseDto.setAge(author.getAge());
        authorResponseDto.setRating(author.getRating());
        authorResponseDto.setCountry(author.getCountry());


        return authorResponseDto;

    }
}
