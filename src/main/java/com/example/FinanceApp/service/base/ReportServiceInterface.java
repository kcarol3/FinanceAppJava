package com.example.FinanceApp.service.base;

import org.springframework.stereotype.Service;

import java.io.IOException;

//tydzien 7, dependency inversion 12
@Service
public interface ReportServiceInterface {
    String generateReport(Long id, String format, String reportType) throws IOException;
}
