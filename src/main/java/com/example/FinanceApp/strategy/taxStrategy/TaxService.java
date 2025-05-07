package com.example.FinanceApp.strategy.taxStrategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaxService {

    private final TaxContext taxContext;

    @Autowired
    public TaxService(TaxContext taxContext) {
        this.taxContext = taxContext;
    }

    public Double calculateTax(Double income, String currencyType) {
        return taxContext.calculateTax(income, currencyType);
    }
}
