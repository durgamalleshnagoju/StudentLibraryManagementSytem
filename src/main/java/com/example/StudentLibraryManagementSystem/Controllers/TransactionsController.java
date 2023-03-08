package com.example.StudentLibraryManagementSystem.Controllers;

import com.example.StudentLibraryManagementSystem.Dtos.BookReturnDto;
import org.springframework.web.bind.annotation.RequestBody;
import com.example.StudentLibraryManagementSystem.Dtos.IssueBookRequestDto;
import com.example.StudentLibraryManagementSystem.Services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transactions")
public class TransactionsController {


    @Autowired
    TransactionService transactionService;

    @PostMapping("issueBook")
    public String issueBook(@RequestBody IssueBookRequestDto issueBookRequestDto){

        try{
            return transactionService.issueBook(issueBookRequestDto);
        }catch (Exception e){
            return e.getMessage();
        }

    }

    @GetMapping("/getTxnInfo")
    public String getTransactionEntry(@RequestParam("bookId")Integer bookId,@RequestParam
            ("cardId")Integer cardId){

        return transactionService.getTransactions(bookId,cardId);
    }
    @PostMapping("/returnBook")
    public String returnBook(@RequestBody BookReturnDto bookReturnDto){
        try{
            return transactionService.returnBook(bookReturnDto);
        } catch (Exception e){
            return e.getMessage();
        }
    }

}