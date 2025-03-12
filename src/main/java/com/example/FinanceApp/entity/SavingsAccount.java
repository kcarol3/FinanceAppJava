package com.example.FinanceApp.entity;

import com.example.FinanceApp.entity.base.Account;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("SAVINGS")
public class SavingsAccount extends Account {

    private double interestRate;

    private SavingsAccount(Builder builder) {
        super(builder);
        this.interestRate = builder.interestRate;
    }

    public SavingsAccount() {

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
