package com.example.FinanceApp.service.base;

import com.example.FinanceApp.dto.SavingGoalDTO;
import com.example.FinanceApp.entity.SavingGoal;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface SavingGoalServiceInterface {
    List<SavingGoal> getAllRootGoals();

    Optional<SavingGoal> getGoalById(Long id);

    SavingGoal createSavingGoal(SavingGoalDTO dto);

    void addSavings(Long id, double amount);
    double getTotalSaved(Long id);
    double getProgress(Long id);
}
