package com.example.FinanceApp.entity;

import com.example.FinanceApp.Composite.base.AccountGroupInterface;
import com.example.FinanceApp.entity.base.Account;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("OWN")
public class OwnAccount extends Account implements AccountGroupInterface {
    public OwnAccount() {

    }

    @Override
    public String toString() {
        return "Own Account: " + this.balance;
    }

    private OwnAccount(OwnAccount.Builder builder) {
        super(builder);
    }

    @Override
    public double getFullBalance() {
        return this.balance;
    }

    @Override
    public String showDetails() {
        return this.toString();
    }

    public static class Builder extends Account.Builder<OwnAccount.Builder> {
        @Override
        protected OwnAccount.Builder self() {
            return this;
        }

        @Override
        public OwnAccount build() {
            return new OwnAccount(this);
        }
    }
}
