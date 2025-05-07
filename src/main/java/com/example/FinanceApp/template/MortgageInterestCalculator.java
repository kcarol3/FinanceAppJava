package com.example.FinanceApp.template;

import org.springframework.stereotype.Component;

@Component
public class MortgageInterestCalculator extends InterestCalculator {

    @Override
    protected void validateCredit(Credit credit) {
        if (credit.getTermInYears() > 40) {
            throw new IllegalArgumentException("Zbyt długi okres kredytowania dla hipoteki.");
        }
    }

    @Override
    protected void calculatePrincipalAmount(Credit credit) {
        // opłata notarialna
    }

    @Override
    protected double calculateInterestAmount(Credit credit) {
        // odsetki roczne = principal × rate × term
        return credit.getPrincipal() * credit.getInterestRate() * credit.getTermInYears();
    }

    @Override
    protected void generateInterestReport(Credit credit, double interest) {
        System.out.println("Obliczono odsetki hipoteczne: " + interest);
    }
}


