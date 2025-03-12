package com.example.FinanceApp.entity;

import com.example.FinanceApp.entity.base.Account;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("OWN")
public class OwnAccount extends Account {
    public OwnAccount() {

    }

    private OwnAccount(OwnAccount.Builder builder) {
        super(builder);
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
