package com.example.FinanceApp.service.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class FileLogger {
    private static final Logger logger = LoggerFactory.getLogger(FileLogger.class);

    public void logToFile(String message) {
        logger.info("[LOG]: " + message);
    }
}
