package com.example.FinanceApp.strategy.assetStrategy;

import com.example.FinanceApp.entity.Asset;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component("incomeValuationStrategy")
public class IncomeValuationStrategy implements AssetValuationStrategy {
    // na podstawie dochodu
    @Override
    public BigDecimal valueAsset(Asset asset, BigDecimal additionalFactors) {
        BigDecimal incomeValue = asset.getAnnualIncome().divide(new BigDecimal("0.05"));
        return incomeValue.multiply(additionalFactors);
    }
}
