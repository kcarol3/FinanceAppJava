package com.example.FinanceApp.decorator;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ValidatorConfig {

    @Bean
    public TransactionValidator transactionValidator() {
        return new AmountGreaterThanZeroDecorator(new BasicTransactionValidator());
    }
}
