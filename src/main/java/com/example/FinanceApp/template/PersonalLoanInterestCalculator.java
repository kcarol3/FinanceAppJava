package com.example.FinanceApp.template;

import org.springframework.stereotype.Component;

@Component
public class PersonalLoanInterestCalculator extends InterestCalculator {

    private static final Integer MAX_CREDIT_VALUE = 100000;
    private static final double PERCENT_VALUE_CREDIT = 0.02;
    private static final Integer MONTHS_IN_YEAR = 12;

    public PersonalLoanInterestCalculator() {
        super(credit -> {
            int totalMonths = credit.getTermInYears() * MONTHS_IN_YEAR;
            return credit.getPrincipal() * (credit.getInterestRate() / MONTHS_IN_YEAR) * totalMonths;
        });
    }

    @Override
    protected void validateCredit(Credit credit) {
        if (credit.getPrincipal() > MAX_CREDIT_VALUE) {
            throw new IllegalArgumentException("Za duży kredyt gotówkowy.");
        }
    }

    @Override
    protected void calculatePrincipalAmount(Credit credit) {
        double fee = credit.getPrincipal() * PERCENT_VALUE_CREDIT;
        credit.setPrincipal(credit.getPrincipal() + fee);
    }

    @Override
    protected void generateInterestReport(Credit credit, double interest) {
        System.out.println("Obliczono odsetki gotówkowe z prowizją: " + interest);
    }
}

//import org.springframework.stereotype.Component;
//
//@Component
//public class PersonalLoanInterestCalculator extends InterestCalculator {
//    private static final Integer MAX_CREDIT_VALUE = 100000;
//    private static final double PERCENT_VALUE_CREDIT = 0.02;
//    private static final Integer MONTHS_IN_YEAR = 12;
//
//    @Override
//    protected void validateCredit(Credit credit) {
//        if (credit.getPrincipal() > MAX_CREDIT_VALUE) {
//            throw new IllegalArgumentException("Za duży kredyt gotówkowy.");
//        }
//    }
//
//    @Override
//    protected void calculatePrincipalAmount(Credit credit) {
//        double fee = credit.getPrincipal() * PERCENT_VALUE_CREDIT;
//        credit.setPrincipal(credit.getPrincipal() + fee);
//    }
//
//    @Override
//    protected double calculateInterestAmount(Credit credit) {
//        int totalMonths = credit.getTermInYears() * MONTHS_IN_YEAR;
//        return credit.getPrincipal() * (credit.getInterestRate() / MONTHS_IN_YEAR) * totalMonths;
//    }
//
//    @Override
//    protected void generateInterestReport(Credit credit, double interest) {
//        System.out.println("Obliczono odsetki gotówkowe z prowizją: " + interest);
//    }
//}


