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

    @GetMapping("/total")
    public double getInvoiceTotal() {
        List<InvoiceItem> invoiceItems = Arrays.asList(
                new InternetService(30.0),
                new PhoneService(50.0)
        );

        InvoiceTotalCalculatorImpl calculator = new InvoiceTotalCalculatorImpl();

        for (InvoiceItem item : invoiceItems) {
            item.accept(calculator);
        }

        return calculator.getTotalAmount();
    }
}

