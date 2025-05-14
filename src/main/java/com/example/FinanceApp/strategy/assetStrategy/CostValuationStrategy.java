package com.example.FinanceApp.strategy.assetStrategy;

import com.example.FinanceApp.entity.Asset;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component("costValuationStrategy")
public class CostValuationStrategy implements AssetValuationStrategy {
    @Override
    public BigDecimal valueAsset(Asset asset, String method, BigDecimal additionalFactor) {
        return asset.getPurchaseCost().multiply(additionalFactor);
    }
}
