package com.example.FinanceApp.strategy.taxStrategy;

import org.springframework.stereotype.Component;

@Component("PLN")
public class StandardTaxCalculationStrategy implements TaxCalculationStrategy {
    public final Double STANDARD_TAX = 0.09;

    @Override
    public Double calculateTax(Double income) {
        return income * STANDARD_TAX;
    }
}
