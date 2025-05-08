package com.example.FinanceApp.strategy;

import com.example.FinanceApp.strategy.taxStrategy.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

// tydzien 7, liskov 3
@ExtendWith(MockitoExtension.class)
public class TaxServiceTest {
    @Mock
    private TaxContext taxContext;

    @InjectMocks
    private TaxService taxService;

    private final Double income = 1000.0;

    @BeforeEach
    void setUp() {
        taxService = new TaxService(taxContext);
    }

    @Test
    void shouldFollowLiskovSubstitutionPrinciple() {
        TaxCalculationStrategy plnStrategy = new StandardTaxCalculationStrategy();
        TaxCalculationStrategy usdStrategy = new USDCalculationStrategy();

        Double plnTax = plnStrategy.calculateTax(income);
        Double usdTax = usdStrategy.calculateTax(income);

        assertEquals(90.0, plnTax);
        assertEquals(210.0, usdTax);
    }
}
