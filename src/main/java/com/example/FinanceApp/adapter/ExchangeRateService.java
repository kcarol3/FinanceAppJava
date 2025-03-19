package com.example.FinanceApp.adapter;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ExchangeRateService {
    private static final Map<String, Double> exchangeRates = new HashMap<String, Double>();

    static {
        exchangeRates.put("USD", 3.85);
        exchangeRates.put("EUR", 4.18);
        exchangeRates.put("GBP", 4.99);
    }

    public Double getExchangeRate(String fromCurrency) {
        switch(fromCurrency) {
            case "USD":
                return exchangeRates.get("USD");
            case "EUR":
                return exchangeRates.get("EUR");
            case "GBP":
                return exchangeRates.get("GBP");
            default:
                return 1.0;
        }
    }
}
