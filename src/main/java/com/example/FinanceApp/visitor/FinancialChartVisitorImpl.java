package com.example.FinanceApp.visitor;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FinancialChartVisitorImpl implements FinancialChartVisitor {
    @Override
    public void visit(Revenue revenue) {
        String content = "Generowanie wykresu dla przychodu: " + revenue.getAmount();
        try (FileWriter writer = new FileWriter("revenueChart.json")) {
            String jsonContent = "{ \"revenueChart\": \"" + content.replace("\n", "\\n") + "\" }";
            writer.write(jsonContent);
            System.out.println("Raport zapisany do pliku JSON.");
        } catch (IOException e) {
            throw new RuntimeException("Błąd podczas zapisywania do pliku JSON");
        }
    }

    @Override
    public void visit(Expense expense) {
        String content = "Generowanie wykresu dla wydatków: " + expense.getAmount();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("expenseChart.txt"))) {
            writer.write(content);
            System.out.println("Wykres został zapisany do pliku TXT.");
        } catch (IOException e) {
            throw new RuntimeException("Błąd podczas zapisywania do pliku JSON");
        }
    }
}
