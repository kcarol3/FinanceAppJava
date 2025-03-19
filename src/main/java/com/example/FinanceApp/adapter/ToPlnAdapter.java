package com.example.FinanceApp.adapter;

import org.springframework.stereotype.Service;

@Service
public class ToPlnAdapter implements CurrencyConverter {
    private ExchangeRateService exchangeRateService;

    public ToPlnAdapter(ExchangeRateService exchangeRateService) {
        this.exchangeRateService = exchangeRateService;
    }

    @Override
    public Double convert(Double amount, String currency) {
        Double exchangeRate = exchangeRateService.getExchangeRate(currency);
        return amount * exchangeRate;
    }
}
