package com.example.FinanceApp.controller;

import com.example.FinanceApp.service.ReportService;
import com.example.FinanceApp.service.base.ReportServiceInterface;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reports")
public class ReportController {
    private final ReportServiceInterface reportService;

    public ReportController(ReportServiceInterface reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/{id}")
    public String generateReport(
            @PathVariable Long id,
            @RequestParam(defaultValue = "txt") String format,
            @RequestParam(defaultValue = "transaction") String reportType) {

        return reportService.generateReport(id, format, reportType);
    }
}
