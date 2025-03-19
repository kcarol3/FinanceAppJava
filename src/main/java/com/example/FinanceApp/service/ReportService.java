package com.example.FinanceApp.service;

import com.example.FinanceApp.bridge.*;
import com.example.FinanceApp.repository.AccountRepository;
import com.example.FinanceApp.service.base.ReportServiceInterface;
import org.springframework.stereotype.Service;

@Service
public class ReportService implements ReportServiceInterface {
    private final AccountRepository accountRepository;

    public ReportService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public String generateReport(Long id, String format, String reportType) {

        if (accountRepository.findById(id) == null) {
            return "Konto o numerze " + id + " nie istnieje.";
        }

        ReportExporter exporter;
        if ("json".equalsIgnoreCase(format)) {
            exporter = new JsonExporter();
        } else {
            exporter = new TxtExporter();
        }

        Report report;
        if ("balance".equalsIgnoreCase(reportType)) {
            report = new BalanceReport(exporter, accountRepository.findById(id).get());
        } else {
            report = new TransactionReport(exporter, accountRepository.findById(id).get());
        }

        report.save();

        return "Raport został wygenerowany dla konta: " + id + " w formacie: " + format;
    }
}
