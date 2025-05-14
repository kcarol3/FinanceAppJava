package com.example.FinanceApp.controller;

import com.example.FinanceApp.visitor.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/invoice")
public class InvoiceController {
    private final Double INTERNET_VALUE = 30.0;
    private final Double PHONE_VALUE = 50.0;

    @GetMapping("/total")
    public double getInvoiceTotal() {
        List<InvoiceItem> invoiceItems = Arrays.asList(
                new InternetService(INTERNET_VALUE),
                new PhoneService(PHONE_VALUE)
        );

        InvoiceTotalCalculatorImpl calculator = new InvoiceTotalCalculatorImpl();

        for (InvoiceItem item : invoiceItems) {
            item.accept(calculator);
        }

        return calculator.getTotalAmount();
    }
}

