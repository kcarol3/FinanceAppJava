package com.example.FinanceApp.strategy.assetStrategy;

import com.example.FinanceApp.entity.Asset;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component("marketValuationStrategy")
public class MarketValuationStrategy implements AssetValuationStrategy {
    // na podstawie ceny rynkowej
    @Override
    public BigDecimal valueAsset(Asset asset, BigDecimal additionalFactors) {
        return asset.getMarketPrice().multiply(additionalFactors);
    }
}
