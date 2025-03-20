package com.example.FinanceApp.entity;

import com.example.FinanceApp.Composite.base.SavingGoalInterface;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class SavingGoal implements SavingGoalInterface {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private double targetAmount;
    private double savedAmount;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private SavingGoal parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private List<SavingGoal> subGoals = new ArrayList<>();

    public SavingGoal() {}

    public SavingGoal(String name, double targetAmount, SavingGoal parent) {
        this.name = name;
        this.targetAmount = targetAmount;
        this.savedAmount = 0;
        this.parent = parent;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getTargetAmount() {
        return targetAmount;
    }

    public void setTargetAmount(double targetAmount) {
        this.targetAmount = targetAmount;
    }

    public double getSavedAmount() {
        return savedAmount;
    }

    public void setSavedAmount(double savedAmount) {
        this.savedAmount = savedAmount;
    }

    public SavingGoal getParent() {
        return parent;
    }

    public void setParent(SavingGoal parent) {
        this.parent = parent;
    }

    public List<SavingGoal> getSubGoals() {
        return subGoals;
    }

    public void setSubGoals(List<SavingGoal> subGoals) {
        this.subGoals = subGoals;
    }

    public void addSubGoal(SavingGoal goal) {
        subGoals.add(goal);
        goal.setParent(this);
    }

    public void updateSavedAmount(double amount) {
        this.savedAmount += amount;
    }

    @Override
    public double getTotalSavedAmount() {
        double total = this.savedAmount;
        for (SavingGoal subGoal : subGoals) {
            total += subGoal.getTotalSavedAmount();
        }
        return total;
    }

    @Override
    public double getProgressPercentage() {
        double totalSaved = getTotalSavedAmount();
        return (totalSaved / targetAmount) * 100;
    }
}
