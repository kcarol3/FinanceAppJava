package com.example.FinanceApp.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

//Tydzień 10, aspekt 5
@Aspect
@Component
public class SynchronizationAspect {

    private final Object lock = new Object();

    @Around("execution(* com.example.FinanceApp.service.base.TransactionService.TransactionCreationServiceInterface.createAndSaveTransaction(..))")
    public Object synchronizeTransaction(ProceedingJoinPoint pjp) throws Throwable {
        synchronized (lock) {
            return pjp.proceed();
        }
    }
}
//Koniec, Tydzień 10, aspekt 5
