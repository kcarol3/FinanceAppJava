package com.example.FinanceApp.bridge;

import com.example.FinanceApp.entity.base.Account;
import com.example.FinanceApp.entity.base.Transaction;
import com.example.FinanceApp.iterator.TransactionIterator;

public class TransactionReport extends Report {
    public TransactionReport(ReportExporter exporter, Account account) {
        super(exporter, account);
    }

    @Override
    public String generateReport() {
        StringBuilder reportContent = new StringBuilder();
        reportContent.append("RAPORT TRANSAKCJI DLA KONTA O IDENTYFIKATORZE: ").append(account.getId()).append("\n");

        TransactionIterator iterator = new TransactionIterator(account.getTransactions(), account.getId());

        while (iterator.hasNext()) {
            Transaction t = iterator.next();
            reportContent.append(t.toString()).append("\n");
        }

        return reportContent.toString();
    }
}
