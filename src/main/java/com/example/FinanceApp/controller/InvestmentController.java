package com.example.FinanceApp.controller;

import com.example.FinanceApp.service.InvestmentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/investment")
public class InvestmentController {
    private final InvestmentService investmentService;

    public InvestmentController(InvestmentService investmentService) {
        this.investmentService = investmentService;
    }

    @PostMapping
    public String invest(@RequestParam String type, @RequestParam String term, @RequestParam double amount) {
        return investmentService.invest(type, term, amount);
    }
}
