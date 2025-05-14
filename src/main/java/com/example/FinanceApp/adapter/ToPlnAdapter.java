package com.example.FinanceApp.adapter;

import org.springframework.stereotype.Service;

// Tydzien 2, Wzorzec Adapter 1, przeliczanie waluty transakcji z różnych typów walut na jeden domyślny dla konta
@Service
public class ToPlnAdapter implements CurrencyConverter {
    private final ExchangeRateService exchangeRateService;

    public ToPlnAdapter(ExchangeRateService exchangeRateService) {
        this.exchangeRateService = exchangeRateService;
    }

    @Override
    public Double convert(Double amount, String currency) {
        Double exchangeRate = exchangeRateService.getExchangeRate(currency);
        return amount * exchangeRate;
    }
}
// Koniec, // Tydzien 2, Wzorzec Adapter 1
