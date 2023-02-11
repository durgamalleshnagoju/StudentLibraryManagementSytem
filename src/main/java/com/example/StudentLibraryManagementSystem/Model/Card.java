package com.example.StudentLibraryManagementSystem.Model;

import com.example.StudentLibraryManagementSystem.Enums.CardStatus;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "card")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @CreationTimestamp
    Date createdOn;

    @UpdateTimestamp
    Date updatedOn;

    @Enumerated(value = EnumType.STRING)
    private CardStatus cardStatus;

    @OneToOne
    @JoinColumn
    private Student StudentVariableName;

    @OneToMany(mappedBy = "card", cascade = CascadeType.ALL)
    private List<Book> BooksIssued;

    public Card() {
        BooksIssued = new ArrayList<>();
    }

    public Card(int id, Date createdOn, Date updatedOn, CardStatus cardStatus, Student studentVariableName) {
        this.id = id;
        this.createdOn = createdOn;
        this.updatedOn = updatedOn;
        this.cardStatus = cardStatus;
        StudentVariableName = studentVariableName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    public CardStatus getCardStatus() {
        return cardStatus;
    }

    public void setCardStatus(CardStatus cardStatus) {
        this.cardStatus = cardStatus;
    }

    public Student getStudentVariableName() {
        return StudentVariableName;
    }

    public void setStudentVariableName(Student studentVariableName) {
        StudentVariableName = studentVariableName;
    }

    public List<Book> getBooksIssued() {
        return BooksIssued;
    }

    public void setBooksIssued(List<Book> booksIssued) {
        BooksIssued = booksIssued;
    }
}
