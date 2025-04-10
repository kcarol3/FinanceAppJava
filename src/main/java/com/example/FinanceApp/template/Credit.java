package com.example.FinanceApp.template;

public class Credit {

    private String type;         // np. "Hipoteczny", "Gotówkowy"
    private double principal;    // Kwota główna kredytu
    private double interestRate; // Oprocentowanie (np. 0.05 = 5%)
    private int termInYears;     // Okres kredytowania w latach

    public Credit(String type, double principal, double interestRate, int termInYears) {
        this.type = type;
        this.principal = principal;
        this.interestRate = interestRate;
        this.termInYears = termInYears;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrincipal() {
        return principal;
    }

    public void setPrincipal(double principal) {
        this.principal = principal;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public int getTermInYears() {
        return termInYears;
    }

    public void setTermInYears(int termInYears) {
        this.termInYears = termInYears;
    }

    @Override
    public String toString() {
        return "Credit{" +
                "type='" + type + '\'' +
                ", principal=" + principal +
                ", interestRate=" + interestRate +
                ", termInYears=" + termInYears +
                '}';
    }
}

