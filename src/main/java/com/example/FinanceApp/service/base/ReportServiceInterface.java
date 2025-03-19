package com.example.FinanceApp.service.base;

import org.springframework.stereotype.Service;

@Service
public interface ReportServiceInterface {
    public String generateReport(Long id, String format, String reportType);
}
