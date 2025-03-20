package com.example.FinanceApp.entity;

import com.example.FinanceApp.Composite.base.AccountGroupInterface;
import com.example.FinanceApp.entity.base.Account;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("SAVINGS")
public class SavingsAccount extends Account implements AccountGroupInterface {

    private double interestRate;

    private SavingsAccount(Builder builder) {
        super(builder);
        this.interestRate = builder.interestRate;
    }

    public SavingsAccount() {

    }

    @Override
    public String toString() {
        return "Savings Account: " + this.balance;
    }

    @Override
    public double getFullBalance() {
        return this.balance;
    }

    @Override
    public String showDetails() {
        return this.toString();
    }

    public static class Builder extends Account.Builder<Builder> {
        private double interestRate;

        public Builder interestRate(double interestRate) {
            this.interestRate = interestRate;
            return this;
        }

        @Override
        protected Builder self() {
            return this;
        }

        @Override
        public SavingsAccount build() {
            return new SavingsAccount(this);
        }
    }
}
