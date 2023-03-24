package com.example.StudentLibraryManagementSystem.Services;

import com.example.StudentLibraryManagementSystem.RequestDto.BookRequestDto;
import com.example.StudentLibraryManagementSystem.Model.Author;
import com.example.StudentLibraryManagementSystem.Model.Book;
import com.example.StudentLibraryManagementSystem.Repositories.AuthorRepository;
import com.example.StudentLibraryManagementSystem.Repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorRepository authorRepository;



    public String addBook(BookRequestDto bookRequestDto)throws Exception{

        int authorId = bookRequestDto.getAuthorId();
        Author author = authorRepository.findById(authorId).get();

        Book book = new Book();
        book.setName(bookRequestDto.getName());
        book.setAuthor(author);
        book.setGenre(bookRequestDto.getGenre());
        book.setPages(bookRequestDto.getPages());
        book.setRating(bookRequestDto.getPages());


        author.getBooksWritten().add(book);

        //book saved by cascading effect
        authorRepository.save(author);


        return "Book added Successfully";
    }

    public String changeStatus(int bookId){
        Book book = bookRepository.findById(bookId).get();
        if(book.isIssued()){
            book.setIssued(false);
            bookRepository.save(book);
            return "book status changed to false";
        } else {
            book.setIssued(true);
            bookRepository.save(book);
            return "book status changed to true";
        }
    }
}
