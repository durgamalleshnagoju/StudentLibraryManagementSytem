package com.example.StudentLibraryManagementSystem.Controllers;

import com.example.StudentLibraryManagementSystem.RequestDto.BookReturnRequestDto;
import com.example.StudentLibraryManagementSystem.ResponceDto.BookResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import com.example.StudentLibraryManagementSystem.RequestDto.IssueBookRequestDto;
import com.example.StudentLibraryManagementSystem.Services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transactions")
public class TransactionsController {


    @Autowired
    TransactionService transactionService;

    @PostMapping("issueBook")
    public ResponseEntity issueBook(@RequestBody IssueBookRequestDto issueBookRequestDto){

        try{
            String response = transactionService.issueBook(issueBookRequestDto);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/getTxnInfo")
    public ResponseEntity getTransactionEntry(@RequestParam("bookId")Integer bookId,@RequestParam
            ("cardId")Integer cardId){
        try{
            String response = transactionService.getTransactions(bookId,cardId);
            return new ResponseEntity<>(response,HttpStatus.FOUND);
        } catch (Exception e){
            return new ResponseEntity<>("Transaction not found", HttpStatus.NOT_FOUND);
        }

    }
    @PostMapping("/returnBook")
    public ResponseEntity returnBook(@RequestBody BookReturnRequestDto bookReturnRequestDto){
        try{
            String response = transactionService.returnBook(bookReturnRequestDto);
            return new ResponseEntity<>(response,HttpStatus.ACCEPTED);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

}