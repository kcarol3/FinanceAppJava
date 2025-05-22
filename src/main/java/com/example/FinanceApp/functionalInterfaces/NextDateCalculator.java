package com.example.FinanceApp.functionalInterfaces;

import java.time.LocalDate;

@FunctionalInterface
public interface NextDateCalculator {
    LocalDate calculateNextDate(LocalDate currentDate);
}

