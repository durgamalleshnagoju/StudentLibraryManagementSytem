package com.example.StudentLibraryManagementSystem.Repositories;

import com.example.StudentLibraryManagementSystem.Model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {

}
