package com.example.FinanceApp.template;

//Tydzien 5, Wzorzec Template 1, obliczanie odsetek roznych rodzai kredytow
public abstract class InterestCalculator {

    public double calculateInterest(Credit credit) {
        validateCredit(credit);
        calculatePrincipalAmount(credit);
        double interest = calculateInterestAmount(credit);
        generateInterestReport(credit, interest);
        return interest;
    }

    protected abstract void validateCredit(Credit credit);

    protected abstract void calculatePrincipalAmount(Credit credit);

    protected abstract double calculateInterestAmount(Credit credit);

    protected abstract void generateInterestReport(Credit credit, double interest);
}
//Koniec, Tydzien 5, Wzorzec Template 1

