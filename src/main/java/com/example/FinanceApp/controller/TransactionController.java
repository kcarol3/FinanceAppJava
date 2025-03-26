package com.example.FinanceApp.controller;

import com.example.FinanceApp.decorator.TransactionValidationException;
import com.example.FinanceApp.dto.TransactionDTO;
import com.example.FinanceApp.entity.base.Transaction;
import com.example.FinanceApp.proxy.LimitingTransactionServiceProxy;
import com.example.FinanceApp.service.base.TransactionServiceInterface;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    private final TransactionServiceInterface transactionService;
    private final LimitingTransactionServiceProxy limitingTransactionServiceProxy;

    public TransactionController(TransactionServiceInterface transactionService, LimitingTransactionServiceProxy limitingTransactionServiceProxy) {
        this.transactionService = transactionService;
        this.limitingTransactionServiceProxy = limitingTransactionServiceProxy;
    }

    @PostMapping
    public ResponseEntity<String> createTransaction(
            @RequestBody TransactionDTO requestDto) {

        try {
            Transaction transaction = limitingTransactionServiceProxy.createAndSaveTransaction(requestDto.getType(), requestDto);
            return new ResponseEntity<>("Transaction created!", HttpStatus.CREATED);
        } catch (TransactionValidationException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/{transactionId}")
    public Transaction createRecurringTransaction(@PathVariable Long transactionId, @RequestParam String frequency) {
        return transactionService.createRecurringTransaction(transactionId, frequency);
    }
}
