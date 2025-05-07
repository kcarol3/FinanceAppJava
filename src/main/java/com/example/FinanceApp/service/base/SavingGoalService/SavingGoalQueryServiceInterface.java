package com.example.FinanceApp.service.base.SavingGoalService;

import com.example.FinanceApp.dto.SavingGoalResponseDTO;

import java.util.List;
import java.util.Optional;

//Tydzień 7, ISP 4
public interface SavingGoalQueryServiceInterface {
    List<SavingGoalResponseDTO> getAllRootGoals();
    List<SavingGoalResponseDTO> getAllGoals();
    Optional<SavingGoalResponseDTO> getGoalById(Long id);
}
//Tydzień 7, ISP 4, koniec
