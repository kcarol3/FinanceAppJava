package com.example.FinanceApp.controller;

import com.example.FinanceApp.mediator.parser.ParserMediatorInterface;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/import")
public class ApiImportController {
    private final ParserMediatorInterface mediator;

    public ApiImportController(ParserMediatorInterface mediator) {
        this.mediator = mediator;
    }

    @PostMapping("/sync")
    public ResponseEntity<String> syncTransactions(@RequestParam("file") MultipartFile file) {
        mediator.sync(file);

        return ResponseEntity.ok("Dane z pliku zostały przetworzone i zapisane.");
    }
}
