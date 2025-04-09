package com.example.FinanceApp.strategy.taxStrategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class TaxContext {

    private TaxCalculationStrategy taxCalculationStrategy;

    private Map<String, TaxCalculationStrategy> strategyMap;

    @Autowired
    public TaxContext(Map<String, TaxCalculationStrategy> strategyMap) {
        this.strategyMap = strategyMap;
    }

    public Double calculateTax(Double income, String strategyName) {
        TaxCalculationStrategy strategy = strategyMap.get(strategyName);
        if (strategy == null) {
            throw new IllegalArgumentException("No such strategy: " + strategyName);
        }
        return strategy.calculateTax(income);
    }
}
