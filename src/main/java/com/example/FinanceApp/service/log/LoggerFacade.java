package com.example.FinanceApp.service.log;

import com.example.FinanceApp.service.base.LoggerFacadeInterface;
import org.springframework.stereotype.Service;

//Tydzień 3, Wzorzec Facade 1, łączenie loggerow konsoli i do pliku w jeden interfejs
@Service
public class LoggerFacade implements LoggerFacadeInterface {
    private final ConsoleLogger consoleLogger;
    private final FileLogger fileLogger;

    public LoggerFacade(ConsoleLogger consoleLogger, FileLogger fileLogger) {
        this.consoleLogger = consoleLogger;
        this.fileLogger = fileLogger;
    }

    public void log(String message) {
        consoleLogger.logToConsole(message);
        fileLogger.logToFile(message);
    }
}
// Koniec, tydzien 3, wzorzec Facade 1