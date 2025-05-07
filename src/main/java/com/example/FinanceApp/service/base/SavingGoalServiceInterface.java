package com.example.FinanceApp.service.base;

import com.example.FinanceApp.dto.SavingGoalDTO;
import com.example.FinanceApp.dto.SavingGoalResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//Tydzień 7, ISP 4 ORYGINAŁ
@Service
public interface SavingGoalServiceInterface {
    List<SavingGoalResponseDTO> getAllRootGoals();
    List<SavingGoalResponseDTO> getAllGoals();
    Optional<SavingGoalResponseDTO> getGoalById(Long id);
    SavingGoalResponseDTO createSavingGoal(SavingGoalDTO dto);
    void addSavings(Long id, double amount);
    double getTotalSaved(Long id);
    double getProgress(Long id);
}
