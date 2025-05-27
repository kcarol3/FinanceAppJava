package com.example.FinanceApp.aspect;

import org.aspectj.lang.JoinPoint;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


//Tydzień 10, aspekt 3
@Aspect
@Component
public class InvestmentRiskSimulationAspect {

    private static final Logger logger = LoggerFactory.getLogger(InvestmentRiskSimulationAspect.class);

    @Before("execution(* com.example.FinanceApp.service.InvestmentService.invest(..))")
    public void simulateInvestmentRisk(JoinPoint joinPoint) {
        double riskLevel = Math.random();

        String type = (String) joinPoint.getArgs()[0];
        String term = (String) joinPoint.getArgs()[1];
        double amount = (Double) joinPoint.getArgs()[2];

        logger.info("Symulacja ryzyka dla inwestycji: typ={}, okres={}, kwota={}, poziom ryzyka={}",
                type, term, amount, String.format("%.2f", riskLevel));

        if (riskLevel > 0.8) {
            logger.warn("⚠️ Wysokie ryzyko inwestycji! Poziom ryzyka: {}", String.format("%.2f", riskLevel));
        }
    }
}
//Koniec, Tydzień 10, aspekt 3

