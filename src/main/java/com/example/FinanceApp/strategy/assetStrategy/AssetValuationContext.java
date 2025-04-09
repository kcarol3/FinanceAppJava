package com.example.FinanceApp.strategy.assetStrategy;

import com.example.FinanceApp.entity.Asset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Map;

@Component
public class AssetValuationContext {

    private final Map<String, AssetValuationStrategy> valuationStrategyMap;

    @Autowired
    public AssetValuationContext(Map<String, AssetValuationStrategy> valuationStrategyMap) {
        this.valuationStrategyMap = valuationStrategyMap;
    }

    public BigDecimal valueAsset(Asset asset, String valuationMethod, BigDecimal additionalFactors) {
        AssetValuationStrategy strategy = valuationStrategyMap.get(valuationMethod);
        if (strategy == null) {
            throw new IllegalArgumentException("No such asset valuation strategy: " + valuationMethod);
        }
        return strategy.valueAsset(asset, additionalFactors);
    }
}

