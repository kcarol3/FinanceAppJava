package com.example.FinanceApp.adapter;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class ExchangeRateService {
    //Tydzień 9, interfejs Function 1
    private static final Double DEFAULT_RATE = 4.99;

    private static final Map<String, Double> exchangeRates = new HashMap<>();

    private final Function<String, Double> exchangeRateFunction =
            currency -> exchangeRates.getOrDefault(currency, DEFAULT_RATE);

    static {
        exchangeRates.put("USD", 3.85);
        exchangeRates.put("EUR", 4.18);
        exchangeRates.put("GBP", 4.99);
    }

    public Double getExchangeRate(String fromCurrency) {
        return exchangeRateFunction.apply(fromCurrency);
    }
    //Koniec, Tydzień 9, interfejs Function 1

//    private static final Double USD_RATE = 3.85;
//    private static final Double EUR_RATE = 4.18;
//    private static final Double GBP_RATE = 4.99;
//    private static final Double DEFAULT_RATE = 4.99;
//    private static final Map<String, Double> exchangeRates = new HashMap<String, Double>();
//
//    static {
//        exchangeRates.put("USD", USD_RATE);
//        exchangeRates.put("EUR", EUR_RATE);
//        exchangeRates.put("GBP", GBP_RATE);
//    }
//
//    public Double getExchangeRate(String fromCurrency) {
//        switch (fromCurrency) {
//            case "USD":
//                return exchangeRates.get("USD");
//            case "EUR":
//                return exchangeRates.get("EUR");
//            case "GBP":
//                return exchangeRates.get("GBP");
//            default:
//                return DEFAULT_RATE;
//        }
//    }
}
