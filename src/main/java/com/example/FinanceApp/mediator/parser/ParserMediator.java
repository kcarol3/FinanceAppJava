package com.example.FinanceApp.mediator.parser;

import com.example.FinanceApp.dto.TransactionDTO;
import com.example.FinanceApp.service.base.TransactionServiceInterface;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;


// Tydzien 4, wzorzec Mediator 2, implementacja mediatora, ktora laczy import transakcji z plikow z zapisem, wybiera odpowiedni parser i na jego podstawie zapisuje dane do bazy
@Service
public class ParserMediator implements ParserMediatorInterface {
    private final TransactionServiceInterface transactionService;
    private final List<Parser> parsers;

    public ParserMediator(TransactionServiceInterface transactionService, List<Parser> parsers) {
        this.transactionService = transactionService;
        this.parsers = parsers;
    }


    @Override
    public void sync(MultipartFile file) {
        try {
            List<TransactionDTO> transactions = this.parseTransaction(file);

            this.processTransaction(transactions);
            System.out.println("Zapisano " + transactions.size() + " transakcji.");
        } catch (Exception e) {
            throw new RuntimeException("Błąd przetwarzania pliku", e);
        }
    }

    private void processTransaction(List<TransactionDTO> transactions) {
        transactions.forEach(transactionDTO -> {
            try {
                transactionService.createAndSaveTransaction(transactionDTO.getType(), transactionDTO);
            } catch (ParseException e) {
                System.out.println(e.getMessage());
            }
        });
    }

    private List<TransactionDTO> parseTransaction(MultipartFile file) throws Exception {
        String contentType = file.getContentType();
//Tydzień 9, stream processing 5
        Parser parser = parsers.stream()
                .filter(p -> p.supports(contentType))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Nieobsługiwany format pliku: " + contentType));
//Koniec, Tydzień 9, stream processing 5
        return parser.parse(file.getInputStream());
    }
}
// Tydzien 4, wzorzec Mediator 2, Koniec
