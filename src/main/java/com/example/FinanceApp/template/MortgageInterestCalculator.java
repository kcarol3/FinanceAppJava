package com.example.FinanceApp.template;

import org.springframework.stereotype.Component;

@Component
public class MortgageInterestCalculator extends InterestCalculator {

    private static final Integer MAX_CREDIT_TERM = 40;
    private static final double PERCENT_VALUE_CREDIT = 0.02;

    public MortgageInterestCalculator() {
        super(credit -> credit.getPrincipal() * credit.getInterestRate() * credit.getTermInYears());
    }

    @Override
    protected void validateCredit(Credit credit) {
        if (credit.getTermInYears() > MAX_CREDIT_TERM) {
            throw new IllegalArgumentException("Zbyt długi okres kredytowania dla hipoteki.");
        }
    }

    @Override
    protected void calculatePrincipalAmount(Credit credit) {
        double principal = credit.getPrincipal();
        double notaryFee = principal * PERCENT_VALUE_CREDIT;
        double totalPrincipal = principal + notaryFee;
        credit.setPrincipal(totalPrincipal);

        System.out.println("Kwota kredytu hipotecznego po doliczeniu opłaty notarialnej: " + totalPrincipal);
    }

    @Override
    protected void generateInterestReport(Credit credit, double interest) {
        System.out.println("Obliczono odsetki hipoteczne: " + interest);
    }
}

//import org.springframework.stereotype.Component;
//
//@Component
//public class MortgageInterestCalculator extends InterestCalculator {
//    private static final Integer MAX_CREDIT_TERM = 40;
//    private static final double PERCENT_VALUE_CREDIT = 0.02;
//
//    @Override
//    protected void validateCredit(Credit credit) {
//        if (credit.getTermInYears() > MAX_CREDIT_TERM) {
//            throw new IllegalArgumentException("Zbyt długi okres kredytowania dla hipoteki.");
//        }
//    }
//
//    @Override
//    protected void calculatePrincipalAmount(Credit credit) {
//        double principal = credit.getPrincipal();
//        double notaryFee = principal * PERCENT_VALUE_CREDIT;
//        double totalPrincipal = principal + notaryFee;
//        credit.setPrincipal(totalPrincipal);
//
//        System.out.println("Kwota kredytu hipotecznego po doliczeniu opłaty notarialnej: " + totalPrincipal);
//    }
//
//    @Override
//    protected double calculateInterestAmount(Credit credit) {
//        return credit.getPrincipal() * credit.getInterestRate() * credit.getTermInYears();
//    }
//
//    @Override
//    protected void generateInterestReport(Credit credit, double interest) {
//        System.out.println("Obliczono odsetki hipoteczne: " + interest);
//    }
//}

