package com.example.FinanceApp.bridge;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class TxtExporter implements ReportExporter {
    @Override
    public void export(String content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("report.txt"))) {
            writer.write(content);
            System.out.println("Raport zapisany do pliku TXT.");
        } catch (IOException e) {
            System.out.println("Błąd podczas zapisywania do pliku TXT: " + e.getMessage());
        }
    }
}
