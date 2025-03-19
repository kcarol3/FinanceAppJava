package com.example.FinanceApp.adapter;

import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Service
public class DateFormatTimeOptionalAdapter implements DateConverter {

    @Override
    public LocalDateTime convertDate(String date) {
        // Określamy formaty daty z i bez godziny
        String dateFormatWithTime = "ISO_LOCAL_DATE_TIME";  // Format z godziną
        String dateFormatWithoutTime = "yyyy-MM-dd";         // Format bez godziny

        DateTimeFormatter formatterWithTime = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        DateTimeFormatter formatterWithoutTime = DateTimeFormatter.ofPattern(dateFormatWithoutTime);

        LocalDateTime parsedDate;

        try {
            // Jeśli data zawiera godzinę, będziemy ją traktować zgodnie z formatem "yyyy-MM-dd HH:mm:ss"
            if (date.length() > 10) {
                parsedDate = LocalDateTime.parse(date, formatterWithTime);  // Parsowanie daty z godziną
            } else {
                // Jeśli data nie zawiera godziny, domyślnie ustawiamy godzinę na 00:00:00
                parsedDate = LocalDateTime.parse(date + "T00:00:00", formatterWithTime);  // Dodajemy godzinę 00:00:00
            }

        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format: " + date);
        }
        return parsedDate;
    }
}
