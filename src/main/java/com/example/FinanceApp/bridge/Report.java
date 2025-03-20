package com.example.FinanceApp.bridge;

import com.example.FinanceApp.entity.base.Account;

// Tydzien 2, Wzorzec Bridge 1 - abstrakcja dla raportu po którym mogą powstawac nowe typy raportów ktore tez implementują ReportExporter
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

// Koniec, Tydzien 2, Wzorzec Bridge 1
