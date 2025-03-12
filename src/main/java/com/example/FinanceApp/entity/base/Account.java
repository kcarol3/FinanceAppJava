package com.example.FinanceApp.entity.base;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // Można zmienić na JOINED lub TABLE_PER_CLASS
@DiscriminatorColumn(name = "account_type", discriminatorType = DiscriminatorType.STRING)
public abstract class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double balance;

    protected Account() {}
    protected Account(Builder<?> builder) {
        this.balance = builder.balance;
    }

    public abstract static class Builder<T extends Builder<T>> {
        private double balance;

        public T balance(double balance) {
            this.balance = balance;
            return self();
        }

        protected abstract T self();

        public abstract Account build();
    }
}
