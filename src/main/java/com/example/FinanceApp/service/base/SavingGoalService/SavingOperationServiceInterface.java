package com.example.FinanceApp.service.base.SavingGoalService;

//Tydzień 7, ISP 4
public interface SavingOperationServiceInterface {
    void addSavings(Long id, double amount);
    double getTotalSaved(Long id);
    double getProgress(Long id);
}
//Tydzień 7, ISP 4, koniec
