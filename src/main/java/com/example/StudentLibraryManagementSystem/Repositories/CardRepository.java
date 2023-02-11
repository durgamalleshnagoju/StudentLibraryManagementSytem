package com.example.StudentLibraryManagementSystem.Repositories;

import com.example.StudentLibraryManagementSystem.Model.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Integer> {

}
