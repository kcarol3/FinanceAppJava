package com.example.FinanceApp.strategy.taxStrategy;

import org.springframework.stereotype.Component;

// Tydzien 5, wzorzec Strategy 1, abstrakcja do obliczania podatku w zaleznosci od waluty
@Component
public interface TaxCalculationStrategy {
    Double calculateTax(Double income);
}
// Tydzien 5, wzorzec Strategy 1, koniec