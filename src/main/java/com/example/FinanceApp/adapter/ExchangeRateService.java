package com.example.FinanceApp.adapter;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ExchangeRateService {
    private static final Double USD_RATE = 3.85;
    private static final Double EUR_RATE = 4.18;
    private static final Double GBP_RATE = 4.99;
    private static final Double DEFAULT_RATE = 4.99;
    private static final Map<String, Double> exchangeRates = new HashMap<String, Double>();

    static {
        exchangeRates.put("USD", USD_RATE);
        exchangeRates.put("EUR", EUR_RATE);
        exchangeRates.put("GBP", GBP_RATE);
    }

    public Double getExchangeRate(String fromCurrency) {
        switch (fromCurrency) {
            case "USD":
                return exchangeRates.get("USD");
            case "EUR":
                return exchangeRates.get("EUR");
            case "GBP":
                return exchangeRates.get("GBP");
            default:
                return DEFAULT_RATE;
        }
    }
}
