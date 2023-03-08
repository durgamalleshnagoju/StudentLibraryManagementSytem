package com.example.StudentLibraryManagementSystem.Services;

import com.example.StudentLibraryManagementSystem.Dtos.BookReturnDto;
import com.example.StudentLibraryManagementSystem.Dtos.IssueBookRequestDto;
import com.example.StudentLibraryManagementSystem.Enums.CardStatus;
import com.example.StudentLibraryManagementSystem.Enums.TransactionStatus;
import com.example.StudentLibraryManagementSystem.Model.Book;
import com.example.StudentLibraryManagementSystem.Model.Card;
import com.example.StudentLibraryManagementSystem.Model.Transactions;
import com.example.StudentLibraryManagementSystem.Repositories.BookRepository;
import com.example.StudentLibraryManagementSystem.Repositories.CardRepository;
import com.example.StudentLibraryManagementSystem.Repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    CardRepository cardRepository;

    public  String issueBook(IssueBookRequestDto issueBookRequestDto)throws Exception{


        int bookId = issueBookRequestDto.getBookId();
        int cardId = issueBookRequestDto.getCardId();

        Book book = bookRepository.findById(bookId).get();

        Card card = cardRepository.findById(cardId).get();

        Transactions transaction = new Transactions();

        //Setting the attributes
        transaction.setBook(book);
        transaction.setCard(card);
        transaction.setTransactionId(UUID.randomUUID().toString());
        transaction.setIssueOperation(true);
        transaction.setTransactionStatus(TransactionStatus.PENDING);



        if(book==null || book.isIssued()){
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepository.save(transaction);
            throw new Exception("Book is not available");

        }

        if(card==null || (!card.getCardStatus().equals(CardStatus.ACTIVATED))){

            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepository.save(transaction);
            throw  new Exception("Card is not valid");
        }



        transaction.setTransactionStatus(TransactionStatus.SUCCESS);


        //set attributes of book
        book.setIssued(true);
        //Btw the book and transaction : bidirectional
        List<Transactions> listOfTransactionForBook = book.getListOfTransactions();
        listOfTransactionForBook.add(transaction);
        book.setListOfTransactions(listOfTransactionForBook);


        // changes in the card
        //Book and the card
        List<Book> issuedBooksForCard = card.getBooksIssued();
        issuedBooksForCard.add(book);
        card.setBooksIssued(issuedBooksForCard);

//        for(Book b: issuedBooksForCard){
//            System.out.println(b.getName());
//        }

        //Card and the Transaction : bidirectional
        List<Transactions> transactionsListForCard = card.getTransactionsList();
        transactionsListForCard.add(transaction);
        card.setTransactionsList(transactionsListForCard);

        //save the parent.
        cardRepository.save(card);

        return "Book issued successfully";
    }

    public String returnBook(BookReturnDto bookReturnDto)throws Exception{

        Book book = bookRepository.findById(bookReturnDto.getBookId()).get();
        Card card = cardRepository.findById(bookReturnDto.getCardId()).get();
        Transactions transactions = new Transactions();
        transactions.setTransactionId(UUID.randomUUID().toString());
        transactions.setTransactionStatus(TransactionStatus.PENDING);
        transactions.setTransactionDate(new Date());
        transactions.setCard(card);
        transactions.setBook(book);
        transactions.setIssueOperation(false);

        // checking validations
        if(!card.getCardStatus().equals(CardStatus.ACTIVATED)){
            transactions.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepository.save(transactions);
            throw new Exception("Card is not activated !");
        }

        List<Book> bookList = card.getBooksIssued();
        if(!bookList.contains(book)){
            transactions.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepository.save(transactions);
            throw new Exception("Book not issued by that card !");
        }

        // if passes the conditions
        transactions.setFine(calculateFine(bookReturnDto));

        transactions.setTransactionStatus(TransactionStatus.SUCCESS);
        // updating parents
        book.getListOfTransactions().add(transactions);
        card.getTransactionsList().add(transactions);

        cardRepository.save(card);

        return "Book returned Successfully !";
    }

    public String getTransactions(int bookId,int cardId){

        List<Transactions> transactionsList = transactionRepository.getTransactionsForBookAndCard(bookId,cardId);

        String transactionId = transactionsList.get(0).getTransactionId();

        return transactionId;
    }

    private int calculateFine(BookReturnDto bookReturnDto){
        int fine = 0;
        int daysGiven = Transactions.noOfDaysAllowed;
        Date LastTransaction = getLastTransaction(bookReturnDto.getBookId(),bookReturnDto.getCardId());
        Date today = new Date();

        long LastTransactionMinutes = LastTransaction.getTime();
        long todayMinutes  = today.getTime();

        long timeDifference = Math.abs(todayMinutes-LastTransactionMinutes);

        long daysDifference = TimeUnit.DAYS.convert(timeDifference, TimeUnit.MILLISECONDS);

        if(daysDifference > daysGiven){
            fine = (int)daysDifference-daysGiven;
        }
        return fine;
    }
    private Date getLastTransaction(int bookId, int cardId){
        List<Transactions> transactionsList = transactionRepository.getTransactionsForBookAndCard(bookId,cardId);
        Date transactionDate = transactionsList.get(0).getTransactionDate();
        for(Transactions transaction : transactionsList){
            if(transaction.getTransactionDate().after(transactionDate) && transaction.isIssueOperation()){
                transactionDate = transaction.getTransactionDate();
            }
        }
        return transactionDate;
    }

}