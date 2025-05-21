package com.example.FinanceApp.template;

import java.util.function.Function;

//Tydzien 5, Wzorzec Template 1, obliczanie odsetek roznych rodzai kredytow
public abstract class InterestCalculator {
    //Tydzień 9, interfejs Function 2
    private final Function<Credit, Double> calculateInterestFunction;

    public InterestCalculator(Function<Credit, Double> calculateInterestFunction) {
        this.calculateInterestFunction = calculateInterestFunction;
    }

    public double calculateInterest(Credit credit) {
        validateCredit(credit);
        calculatePrincipalAmount(credit);
        double interest = calculateInterestFunction.apply(credit);
        generateInterestReport(credit, interest);
        return interest;
    }

    protected abstract void validateCredit(Credit credit);

    protected abstract void calculatePrincipalAmount(Credit credit);

    protected abstract void generateInterestReport(Credit credit, double interest);
    //Koniec, Tydzień 9, interfejs Function 2

}
//    public double calculateInterest(Credit credit) {
//        validateCredit(credit);
//        calculatePrincipalAmount(credit);
//        double interest = calculateInterestAmount(credit);
//        generateInterestReport(credit, interest);
//        return interest;
//    }
//
//    protected abstract void validateCredit(Credit credit);
//
//    protected abstract void calculatePrincipalAmount(Credit credit);
//
//    protected abstract double calculateInterestAmount(Credit credit);
//
//    protected abstract void generateInterestReport(Credit credit, double interest);
//
//Koniec, Tydzien 5, Wzorzec Template 1

