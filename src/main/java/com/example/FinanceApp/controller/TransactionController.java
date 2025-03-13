package com.example.FinanceApp.controller;

import com.example.FinanceApp.dto.TransactionDTO;
import com.example.FinanceApp.entity.RecurringTransaction;
import com.example.FinanceApp.entity.base.Transaction;
import com.example.FinanceApp.service.base.TransactionServiceInterface;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    private final TransactionServiceInterface transactionService;

    public TransactionController(TransactionServiceInterface transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public ResponseEntity<Transaction> createTransaction(
            @RequestBody TransactionDTO requestDto) {
        Transaction transaction = transactionService.createAndSaveTransaction(requestDto.getType(), requestDto);
        return ResponseEntity.ok(transaction);
    }

    @PostMapping("/{transactionId}")
    public Transaction createRecurringTransaction(@PathVariable Long transactionId, @RequestParam String frequency) {
        return transactionService.createRecurringTransaction(transactionId, frequency);
    }
}
