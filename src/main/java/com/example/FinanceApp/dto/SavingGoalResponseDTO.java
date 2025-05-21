package com.example.FinanceApp.dto;


import java.util.List;
import java.util.stream.Collectors;

import com.example.FinanceApp.entity.SavingGoal;


public class SavingGoalResponseDTO {
    private Long id;
    private String name;
    private double targetAmount;
    private double savedAmount;
    private Long parentId;
    private List<SavingGoalResponseDTO> subGoals;

    public SavingGoalResponseDTO(SavingGoal savingGoal) {
        this.id = savingGoal.getId();
        this.name = savingGoal.getName();
        this.targetAmount = savingGoal.getTargetAmount();
        this.savedAmount = savingGoal.getSavedAmount();
        this.parentId = (savingGoal.getParent() != null) ? savingGoal.getParent().getId() : null;
        //Tydzień 9, stream processing 3
        this.subGoals = savingGoal.getSubGoals().stream()
                .map(SavingGoalResponseDTO::new)
                .collect(Collectors.toList());
        //Koniec, Tydzień 9, stream processing 3
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getTargetAmount() {
        return targetAmount;
    }

    public double getSavedAmount() {
        return savedAmount;
    }

    public Long getParentId() {
        return parentId;
    }

    public List<SavingGoalResponseDTO> getSubGoals() {
        return subGoals;
    }
}

