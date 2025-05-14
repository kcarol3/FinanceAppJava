package com.example.FinanceApp.bridge;

import com.example.FinanceApp.entity.base.Account;

import java.io.IOException;

// Tydzien 2, Wzorzec Bridge 1 - abstrakcja dla raportu po którym mogą powstawac nowe typy raportów ktore tez implementują ReportExporter
// tydzien 7, dependency inversion 5
public abstract class Report {
    protected ReportExporter exporter;
    protected Account account;

    public Report(ReportExporter exporter, Account account) {
        this.exporter = exporter;
        this.account = account;
    }

    public abstract String generateReport();

    public void save() throws IOException {
        exporter.export(generateReport());
    }
}

// Koniec, Tydzien 2, Wzorzec Bridge 1
