package com.example.FinanceApp.aspect;


import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;


//Tydzień 10, aspekt 6
@Aspect
@Component
public class PositiveAmountAspect {

    @Before(value = "execution(* com.example.FinanceApp.service.base.SavingGoalServiceInterface.addSavings(..)) && args(id, amount)", argNames = "id,amount")
    public void checkPositiveAmount(Long id, double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Kwota musi być większa od zera!");
        }
    }
}
//Koniec, Tydzień 10, aspekt 6

