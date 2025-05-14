package com.example.FinanceApp.bridge;

import java.io.FileWriter;
import java.io.IOException;

public class JsonExporter implements ReportExporter {
    @Override
    public void export(String content) throws IOException {
        try (FileWriter writer = new FileWriter("report.json")) {
            String jsonContent = "{ \"report\": \"" + content.replace("\n", "\\n") + "\" }";
            writer.write(jsonContent);
            System.out.println("Raport zapisany do pliku JSON.");
        } catch (IOException e) {
            throw new IOException("Błąd podczas zapisywania do pliku JSON: " + e.getMessage());
        }
    }
}
