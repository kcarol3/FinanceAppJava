package com.example.FinanceApp.strategy.taxStrategy;

import org.springframework.stereotype.Component;

// Tydzien 6, SRP 4
@Component("USD")
public class USDCalculationStrategy implements TaxCalculationStrategy{
    public final Double USD_TAX = 0.21;
    @Override
    public Double calculateTax(Double income) {
        return income * USD_TAX;
    }
}
// Tydzien 6, SRP 4, koniec