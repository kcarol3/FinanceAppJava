package com.example.FinanceApp.controller;

import com.example.FinanceApp.visitor.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/chart")
public class FinancialChartController {

    @GetMapping("/generate")
    public ResponseEntity<String> generateFinancialCharts() {
        List<FinancialData> financialDataList = Arrays.asList(
                new Revenue(1500.0),
                new Expense(800.0)
        );

        FinancialChartVisitorImpl chartGenerator = new FinancialChartVisitorImpl();

        for (FinancialData data : financialDataList) {
            data.accept(chartGenerator);
        }

        return new ResponseEntity<>("Wykresy zostały wygenerowane!",HttpStatus.OK);
    }
}
