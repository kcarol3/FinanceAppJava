package com.example.FinanceApp.controller;

import com.example.FinanceApp.decorator.TransactionValidationException;
import com.example.FinanceApp.dto.TransactionDTO;
import com.example.FinanceApp.entity.base.Transaction;
import com.example.FinanceApp.flyweight.transactionIcon.TransactionIconFactory;
import com.example.FinanceApp.proxy.LimitingTransactionServiceProxy;
import com.example.FinanceApp.service.base.TransactionServiceInterface;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.ParseException;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    private final TransactionServiceInterface transactionService;
    private final LimitingTransactionServiceProxy limitingTransactionServiceProxy;
    private final TransactionIconFactory transactionIconFactory;

    public TransactionController(TransactionServiceInterface transactionService, LimitingTransactionServiceProxy limitingTransactionServiceProxy, TransactionIconFactory transactionIconFactory) {
        this.transactionService = transactionService;
        this.limitingTransactionServiceProxy = limitingTransactionServiceProxy;
        this.transactionIconFactory = transactionIconFactory;
    }

    //Tydzień 8, obsługa wyjątku 1, wyjątku 2
    @PostMapping
    public ResponseEntity<String> createTransaction(@RequestBody TransactionDTO requestDto) {
        try {
            limitingTransactionServiceProxy.createAndSaveTransaction(requestDto.getType(), requestDto);
        } catch (TransactionValidationException | ParseException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Transaction created!", HttpStatus.CREATED);
    }
    //Koniec, tydzień 8, obsługa wyjątku 1, wyjątku 2

    @GetMapping("/{accountId}")
    public ResponseEntity<String> getSumOfExpenses(@PathVariable Long accountId) {
        double sumOfExpenses = transactionService.calculateTotalExpenses(accountId);
        return new ResponseEntity<>("Sum of expenses: " + sumOfExpenses, HttpStatus.OK);
    }


    @PostMapping("/{transactionId}")
    public Transaction createRecurringTransaction(@PathVariable Long transactionId, @RequestParam String frequency) {
        return transactionService.createRecurringTransaction(transactionId, frequency);
    }

    //Tydzień 8, obsługa wyjątku 3
    @GetMapping("/{type}/image")
    public ResponseEntity<byte[]> getTransactionImage(@PathVariable String type) {
        BufferedImage image = transactionIconFactory.getTransactionIcon(type.toUpperCase()).getImage();
        try {
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.IMAGE_PNG_VALUE)
                    .body(convertImageToByteArray(image));
        } catch(IOException e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //Koniec, tydzień 8, obsługa wyjątku 3

    private byte[] convertImageToByteArray(BufferedImage image) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(image, "PNG", byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }
}
