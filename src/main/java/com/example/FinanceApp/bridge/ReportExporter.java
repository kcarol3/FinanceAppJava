package com.example.FinanceApp.bridge;

import java.io.IOException;

public interface ReportExporter {
    void export(String data) throws IOException;
}
