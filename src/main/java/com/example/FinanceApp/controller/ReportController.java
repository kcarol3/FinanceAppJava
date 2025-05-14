package com.example.FinanceApp.controller;

import com.example.FinanceApp.service.base.ReportServiceInterface;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/reports")
public class ReportController {
    private final ReportServiceInterface reportService;

    public ReportController(ReportServiceInterface reportService) {
        this.reportService = reportService;
    }

    //Tydzień 8, obsługa wyjątku 4
    @GetMapping("/{id}")
    public String generateReport(
            @PathVariable Long id,
            @RequestParam(defaultValue = "txt") String format,
            @RequestParam(defaultValue = "transaction") String reportType) {
        try {
            return reportService.generateReport(id, format, reportType);
        } catch(IOException e){
            return e.getMessage();
        }
    }
    //Koniec, tydzień 8, obsługa wyjątku 4
}
