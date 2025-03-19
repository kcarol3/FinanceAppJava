package com.example.FinanceApp.adapter;

import java.text.ParseException;
import java.time.LocalDateTime;

public interface DateConverter {
    LocalDateTime convertDate(String date);
}
