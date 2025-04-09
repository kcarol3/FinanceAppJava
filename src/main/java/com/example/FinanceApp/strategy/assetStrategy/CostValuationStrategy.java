package com.example.FinanceApp.strategy.assetStrategy;

import com.example.FinanceApp.entity.Asset;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component("costValuationStrategy")
public class CostValuationStrategy implements AssetValuationStrategy {
    // na podstawie kosztu
    @Override
    public BigDecimal valueAsset(Asset asset, BigDecimal additionalFactors) {
        return asset.getPurchaseCost().multiply(additionalFactors);
    }
}
