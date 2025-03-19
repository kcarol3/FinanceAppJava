package com.example.FinanceApp.bridge;

import com.example.FinanceApp.entity.base.Account;

public class BalanceReport extends Report {
    public BalanceReport(ReportExporter exporter, Account account) {
        super(exporter, account);
    }

    @Override
    public String generateReport() {
        double balance = account.getBalance();
        return "RAPORT SALDA DLA KONTA O IDENTYFIKATORZE: " + account.getId() +
                " | AKTUALNE SALDO: " + balance + " PLN";
    }
}
