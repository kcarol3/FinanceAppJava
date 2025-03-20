package com.example.FinanceApp.service;

import com.example.FinanceApp.dto.SavingGoalDTO;
import com.example.FinanceApp.dto.SavingGoalResponseDTO;
import com.example.FinanceApp.entity.SavingGoal;
import com.example.FinanceApp.repository.SavingGoalRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SavingGoalService {

    private final SavingGoalRepository repository;

    public SavingGoalService(SavingGoalRepository repository) {
        this.repository = repository;
    }

    public List<SavingGoalResponseDTO> getAllRootGoals() {
        return repository.findByParentIsNull().stream()
                .map(SavingGoalResponseDTO::new)
                .collect(Collectors.toList());
    }

    public List<SavingGoalResponseDTO> getAllGoals() {
        return repository.findAll().stream()
                .map(SavingGoalResponseDTO::new)
                .collect(Collectors.toList());
    }

    public Optional<SavingGoalResponseDTO> getGoalById(Long id) {
        return repository.findById(id).map(SavingGoalResponseDTO::new);
    }

    public SavingGoalResponseDTO createSavingGoal(SavingGoalDTO dto) {
        SavingGoal parent = dto.getParentId() != null ? repository.findById(dto.getParentId()).orElse(null) : null;
        SavingGoal goal = new SavingGoal(dto.getName(), dto.getTargetAmount(), parent);
        return new SavingGoalResponseDTO(repository.save(goal));
    }

    public void addSavings(Long id, double amount) {
        SavingGoal goal = repository.findById(id).orElseThrow();
        goal.updateSavedAmount(amount);
        repository.save(goal);
    }

    public double getTotalSaved(Long id) {
        SavingGoal goal = repository.findById(id).orElseThrow();
        return goal.getTotalSavedAmount();
    }

    public double getProgress(Long id) {
        SavingGoal goal = repository.findById(id).orElseThrow();
        return goal.getProgressPercentage();
    }
}


