package com.example.StudentLibraryManagementSystem.Repositories;

import com.example.StudentLibraryManagementSystem.Model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Integer> {

}
