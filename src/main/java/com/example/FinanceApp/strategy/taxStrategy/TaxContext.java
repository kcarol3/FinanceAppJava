package com.example.FinanceApp.strategy.taxStrategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.function.Function;

// Tydzien 6, Open-closed 3
@Component
public class TaxContext {

    // abstrakcja
//    private TaxCalculationStrategy taxCalculationStrategy;
//
//    private Map<String, TaxCalculationStrategy> strategyMap;
//
//    @Autowired
//    public TaxContext(Map<String, TaxCalculationStrategy> strategyMap) {
//        this.strategyMap = strategyMap;
//    }
//
//    public Double calculateTax(Double income, String strategyName) {
//        TaxCalculationStrategy strategy = strategyMap.get(strategyName);
//        if (strategy == null) {
//            throw new IllegalArgumentException("No such strategy: " + strategyName);
//        }
//        return strategy.calculateTax(income);
//    }

    // sterownaie danymi
    private final Map<String, Function<Double, Double>> taxStrategies;

    public TaxContext() {
        this.taxStrategies = Map.of(
                "Standard", income -> income * 0.9,
                "USD", income -> income * 0.21,
                "EUR", income -> income * 0.3
        );
    }

    public Double calculateTax(Double income, String strategyName) {
        Function<Double, Double> strategy = taxStrategies.get(strategyName.toUpperCase());
        if (strategy == null) {
            throw new IllegalArgumentException("No such strategy: " + strategyName);
        }
        return strategy.apply(income);
    }

    // Tydzien 6, Open-closed 3, koniec
}
