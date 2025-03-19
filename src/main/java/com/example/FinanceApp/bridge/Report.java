package com.example.FinanceApp.bridge;

import com.example.FinanceApp.entity.base.Account;

public abstract class Report {
    protected ReportExporter exporter;
    protected Account account;

    public Report(ReportExporter exporter, Account account) {
        this.exporter = exporter;
        this.account = account;
    }

    public abstract String generateReport();

    public void save() {
        exporter.export(generateReport());
    }
}
