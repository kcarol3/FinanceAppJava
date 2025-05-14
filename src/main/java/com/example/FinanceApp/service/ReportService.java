package com.example.FinanceApp.service;

import com.example.FinanceApp.bridge.*;
import com.example.FinanceApp.repository.AccountRepository;
import com.example.FinanceApp.service.base.ReportServiceInterface;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ReportService implements ReportServiceInterface {
    private final AccountRepository accountRepository;

    public ReportService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public String generateReport(Long id, String format, String reportType) throws IOException {
        if (accountRepository.findById(id) == null) {
            return "Konto o numerze " + id + " nie istnieje.";
        }

        ReportExporter exporter = getReportExporter(format);
        // tydzien 7, dependency inversion 5, wykorzystanie
        Report report = getReport(id, reportType, exporter);
        report.save();

        return "Raport został wygenerowany dla konta: " + id + " w formacie: " + format;
    }

    private Report getReport(Long id, String reportType, ReportExporter exporter) {
        Report report;
        if ("balance".equalsIgnoreCase(reportType)) {
            report = new BalanceReport(exporter, accountRepository.findById(id).get());
        } else {
            report = new TransactionReport(exporter, accountRepository.findById(id).get());
        }
        return report;
    }

    private ReportExporter getReportExporter(String format) {
        ReportExporter exporter;
        if ("json".equalsIgnoreCase(format)) {
            exporter = new JsonExporter();
        } else {
            exporter = new TxtExporter();
        }
        return exporter;
    }
}
