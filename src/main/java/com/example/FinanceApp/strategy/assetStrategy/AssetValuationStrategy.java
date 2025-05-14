package com.example.FinanceApp.strategy.assetStrategy;

import com.example.FinanceApp.entity.Asset;

import java.math.BigDecimal;

// Tydzien 5, wzorzec Strategy 2, abstrakcja dla obliczania wyceny aktywow posiadanych
public interface AssetValuationStrategy {
    BigDecimal valueAsset(Asset asset, String method, BigDecimal additionalFactor);
}
// Tydzien 5, wzorzec Strategy 2, koniec
