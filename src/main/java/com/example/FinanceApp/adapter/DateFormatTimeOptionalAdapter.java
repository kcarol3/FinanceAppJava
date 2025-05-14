package com.example.FinanceApp.adapter;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

// Tydzien 2, Wzorzec adapter 2 - ujednolicenie formatu daty dla stringow date lub datetime, wynikowo zawsze bedzie zapisany obiket LocalDateTime
@Service
public class DateFormatTimeOptionalAdapter implements DateConverter {
    private final int MINIMAL_LENGHT = 10;

    @Override
    public LocalDateTime convertDate(String date) {
        DateTimeFormatter formatterWithTime = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        LocalDateTime parsedDate;

        try {
            if (date.length() > this.MINIMAL_LENGHT) {
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

//Koniec, Tydzien 2, Wzorzec adapter 2
