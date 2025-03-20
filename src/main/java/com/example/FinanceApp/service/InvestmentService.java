package com.example.FinanceApp.service;

import com.example.FinanceApp.bridge.*;
import com.example.FinanceApp.service.base.InvestmentServiceInterface;
import org.springframework.stereotype.Service;

@Service
public class InvestmentService implements InvestmentServiceInterface {
    private final StockInvestment stockInvestment;
    private final RealEstateInvestment realEstateInvestment;

    public InvestmentService(StockInvestment stockInvestment, RealEstateInvestment realEstateInvestment) {
        this.stockInvestment = stockInvestment;
        this.realEstateInvestment = realEstateInvestment;
    }

    public String invest(String type, String term, double amount) {
        InvestmentType investmentType;
        if ("stocks".equalsIgnoreCase(type)) {
            investmentType = stockInvestment;
        } else if ("realEstate".equalsIgnoreCase(type)) {
            investmentType = realEstateInvestment;
        } else {
            throw new IllegalArgumentException("Unknown term of investment");
        }

        Investment investment;
        if ("long".equalsIgnoreCase(term)) {
            investment = new LongTermInvestment(investmentType);
        } else if ("short".equalsIgnoreCase(term)) {
            investment = new ShortTermInvestment(investmentType);
        } else {
            throw new IllegalArgumentException("Unknown term of investment term");
        }

        return investment.makeInvestment(amount);
    }
}
