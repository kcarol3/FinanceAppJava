package com.example.FinanceApp.template;

import org.springframework.stereotype.Component;

@Component
public class PersonalLoanInterestCalculator extends InterestCalculator {

    @Override
    protected void validateCredit(Credit credit) {
        if (credit.getPrincipal() > 100000) {
            throw new IllegalArgumentException("Za duży kredyt gotówkowy.");
        }
    }

    @Override
    protected void calculatePrincipalAmount(Credit credit) {
        // doliczamy prowizję 2%
        double fee = credit.getPrincipal() * 0.02;
        credit.setPrincipal(credit.getPrincipal() + fee);
    }

    @Override
    protected double calculateInterestAmount(Credit credit) {
        // odsetki = principal * rate * (term * 12 / 12)
        int totalMonths = credit.getTermInYears() * 12;
        return credit.getPrincipal() * (credit.getInterestRate() / 12) * totalMonths;
    }

    @Override
    protected void generateInterestReport(Credit credit, double interest) {
        System.out.println("Obliczono odsetki gotówkowe z prowizją: " + interest);
    }
}


