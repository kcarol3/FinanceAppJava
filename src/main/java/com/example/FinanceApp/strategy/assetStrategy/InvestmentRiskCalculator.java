package com.example.FinanceApp.strategy.assetStrategy;

import com.example.FinanceApp.entity.base.User;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class InvestmentRiskCalculator {

    public BigDecimal calculateInvestmentRisk(User user, BigDecimal totalPortfolioValue) {
        return totalPortfolioValue.multiply(user.getRiskFactor());
    }
}
