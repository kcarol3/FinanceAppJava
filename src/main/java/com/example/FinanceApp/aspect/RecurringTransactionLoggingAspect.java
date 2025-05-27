package com.example.FinanceApp.aspect;

import com.example.FinanceApp.entity.base.Transaction;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

//Tydzień 10, aspekt 2
@Aspect
@Component
public class RecurringTransactionLoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(RecurringTransactionLoggingAspect.class);

    @AfterReturning(
            pointcut = "execution(* com.example.FinanceApp.service.base.TransactionService.RecurringTransactionServiceInterface.createRecurringTransaction(..))",
            returning = "transaction"
    )
    public void logRecurringTransaction(JoinPoint joinPoint, Transaction transaction) {
        Long transactionId = (Long) joinPoint.getArgs()[0];
        String frequency = (String) joinPoint.getArgs()[1];

        logger.info("Utworzono powtarzającą się transakcję: ID oryginalnej transakcji = {}, Częstotliwość = {}, Nowe ID = {}",
                transactionId, frequency, transaction.getId());
    }
}
//Koniec, Tydzień 10, aspekt 2
