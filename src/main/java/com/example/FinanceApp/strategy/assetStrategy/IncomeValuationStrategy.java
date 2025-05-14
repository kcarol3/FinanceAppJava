package com.example.FinanceApp.strategy.assetStrategy;

import com.example.FinanceApp.entity.Asset;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component("incomeValuationStrategy")
public class IncomeValuationStrategy implements AssetValuationStrategy {
    @Override
    public BigDecimal valueAsset(Asset asset, String method, BigDecimal additionalFactor) {
        BigDecimal incomeValue = asset.getAnnualIncome().divide(new BigDecimal("0.05"), RoundingMode.UP);
        return incomeValue.multiply(additionalFactor);
    }
}
