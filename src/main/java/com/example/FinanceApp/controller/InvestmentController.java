package com.example.FinanceApp.controller;

import com.example.FinanceApp.entity.Asset;
import com.example.FinanceApp.entity.base.User;
import com.example.FinanceApp.repository.AssetRepository;
import com.example.FinanceApp.repository.UserRepository;
import com.example.FinanceApp.service.InvestmentService;
import com.example.FinanceApp.strategy.assetStrategy.AssetValuationContext;
import com.example.FinanceApp.strategy.assetStrategy.InvestmentRiskCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/investment")
public class InvestmentController {

    private final AssetRepository assetRepository;
    private final UserRepository userRepository;
    private final AssetValuationContext valuationContext;
    private final InvestmentRiskCalculator riskCalculator;

    @Autowired
    public InvestmentController(AssetRepository assetRepository,
                                UserRepository userRepository,
                                AssetValuationContext valuationContext,
                                InvestmentRiskCalculator riskCalculator) {
        this.assetRepository = assetRepository;
        this.userRepository = userRepository;
        this.valuationContext = valuationContext;
        this.riskCalculator = riskCalculator;
    }

    @PostMapping("/valueAssets")
    public BigDecimal valueAssets(@RequestParam Long userId,
                                  @RequestParam String valuationMethod,
                                  @RequestParam BigDecimal additionalFactors) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
        List<Asset> assets = assetRepository.findAll();

        BigDecimal totalValue = BigDecimal.ZERO;
        for (Asset asset : assets) {
            totalValue = totalValue.add(valuationContext.valueAsset(asset, valuationMethod, additionalFactors));
        }
        return totalValue;
    }

    @PostMapping("/calculateRisk")
    public BigDecimal calculateRisk(@RequestParam Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
        BigDecimal totalPortfolioValue = user.getTotalInvestment();

        return riskCalculator.calculateInvestmentRisk(user, totalPortfolioValue);
    }
}

