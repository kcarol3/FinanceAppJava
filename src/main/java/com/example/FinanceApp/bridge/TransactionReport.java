package com.example.FinanceApp.bridge;

import com.example.FinanceApp.entity.base.Account;
import com.example.FinanceApp.entity.base.Transaction;

public class TransactionReport extends Report {
    public TransactionReport(ReportExporter exporter, Account account) {
        super(exporter, account);
    }

    @Override
    public String generateReport() {
        StringBuilder reportContent = new StringBuilder();
        reportContent.append("RAPORT TRANSAKCJI DLA KONTA O IDENTYFIKATORZE: ").append(account.getId()).append("\n");

        for (Transaction t : account.getTransactions()) {
            reportContent.append(t.toString()).append("\n");
        }

        return reportContent.toString();
    }
}
