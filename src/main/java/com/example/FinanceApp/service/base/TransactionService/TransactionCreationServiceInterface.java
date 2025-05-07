package com.example.FinanceApp.service.base.TransactionService;

import com.example.FinanceApp.dto.TransactionDTO;
import com.example.FinanceApp.entity.base.Transaction;

import java.text.ParseException;

//Tydzień 7, ISP 5
public interface TransactionCreationServiceInterface {
    Transaction createAndSaveTransaction(String type, TransactionDTO transactionDto) throws ParseException;
}
//Tydzień 7, ISP 5, koniec