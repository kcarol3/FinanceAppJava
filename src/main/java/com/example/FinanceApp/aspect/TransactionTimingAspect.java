package com.example.FinanceApp.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

//Tydzień 10, aspekt 1
@Aspect
@Component
public class TransactionTimingAspect {

    @Around("execution(* com.example.FinanceApp.service.base.AccountService.MoneyTransferServiceInterface.transferMoney(..))")
    public Object measureTransferTime(ProceedingJoinPoint pjp) throws Throwable {
        long startTime = System.currentTimeMillis();

        Object result = pjp.proceed();

        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;

        System.out.println("Czas realizacji transakcji: " + duration + " ms");

        return result;
    }
}
//Koniec, Tydzień 10, aspekt 1

