package com.example.FinanceApp.strategy.assetStrategy;

import com.example.FinanceApp.entity.Asset;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;
import java.util.function.BiFunction;

// Tydzien 6, Open-closed 4
@Component
public class AssetValuationContext {
    // abstrakcja
//    private final Map<String, AssetValuationStrategy> valuationStrategyMap;
//
//    @Autowired
//    public AssetValuationContext(Map<String, AssetValuationStrategy> valuationStrategyMap) {
//        this.valuationStrategyMap = valuationStrategyMap;
//    }
//
//    public BigDecimal valueAsset(Asset asset, String valuationMethod, BigDecimal additionalFactors) {
//        AssetValuationStrategy strategy = valuationStrategyMap.get(valuationMethod);
//        if (strategy == null) {
//            throw new IllegalArgumentException("No such asset valuation strategy: " + valuationMethod);
//        }
//        return strategy.valueAsset(asset, additionalFactors);
//    }

    //sterowanie danymi
    private final Map<String, BiFunction<Asset, BigDecimal, BigDecimal>> valuationStrategies = Map.of(
            "COST", (asset, factor) -> asset.getPurchaseCost().multiply(factor),

            "INCOME", (asset, factor) -> {
                BigDecimal incomeValue = asset.getAnnualIncome().divide(new BigDecimal("0.05"), RoundingMode.HALF_UP);
                return incomeValue.multiply(factor);
            },

            "MARKET", (asset, factor) -> asset.getMarketPrice().multiply(factor)
    );

    public BigDecimal valueAsset(Asset asset, String method, BigDecimal additionalFactor) {
        BiFunction<Asset, BigDecimal, BigDecimal> strategy = valuationStrategies.get(method.toUpperCase());
        if (strategy == null) {
            throw new IllegalArgumentException("Brak strategii wyceny: " + method);
        }
        return strategy.apply(asset, additionalFactor);
    }
}
// Tydzien 6, Open-closed 4, koniec

