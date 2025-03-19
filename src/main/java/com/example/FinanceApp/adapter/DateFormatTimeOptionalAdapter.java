package com.example.FinanceApp.adapter;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Service
public class DateFormatTimeOptionalAdapter implements DateConverter {

    @Override
    public LocalDateTime convertDate(String date) {
        DateTimeFormatter formatterWithTime = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        LocalDateTime parsedDate;

        try {
            if (date.length() > 10) {
                parsedDate = LocalDateTime.parse(date, formatterWithTime);
            } else {
                parsedDate = LocalDateTime.parse(date + "T00:00:00", formatterWithTime);
            }

        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format: " + date);
        }
        return parsedDate;
    }
}
