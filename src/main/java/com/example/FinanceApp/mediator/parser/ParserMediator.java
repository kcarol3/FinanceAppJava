package com.example.FinanceApp.mediator.parser;

import com.example.FinanceApp.dto.TransactionDTO;
import com.example.FinanceApp.service.base.TransactionServiceInterface;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
            String contentType = file.getContentType();

            Parser parser = parsers.stream()
                    .filter(p -> p.supports(contentType))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Nieobsługiwany format pliku: " + contentType));

            List<TransactionDTO> transactions = parser.parse(file.getInputStream());

            transactions.forEach(transactionDTO -> {
                try {
                    transactionService.createAndSaveTransaction(transactionDTO.getType(), transactionDTO);
                } catch (ParseException e) {
                    throw new RuntimeException("Blad konwersji");
                }
            });

            System.out.println("Zapisano " + transactions.size() + " transakcji.");
        } catch (Exception e) {
            throw new RuntimeException("Błąd przetwarzania pliku", e);
        }
    }
}
// Tydzien 4, wzorzec Mediator 2, Koniec
