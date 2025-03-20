package com.example.FinanceApp.controller;

import com.example.FinanceApp.dto.SavingGoalDTO;
import com.example.FinanceApp.dto.SavingGoalResponseDTO;
import com.example.FinanceApp.entity.SavingGoal;
import com.example.FinanceApp.service.SavingGoalService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/saving-goals")
public class SavingGoalController {

    private final SavingGoalService savingGoalService;

    public SavingGoalController(SavingGoalService savingGoalService) {
        this.savingGoalService = savingGoalService;
    }

    @GetMapping
    public List<SavingGoalResponseDTO> getRootGoals() {
        return savingGoalService.getAllRootGoals();
    }

    @GetMapping("/all")
    public List<SavingGoalResponseDTO> getAllGoals() {
        return savingGoalService.getAllGoals();
    }

    @GetMapping("/{id}")
    public Optional<SavingGoalResponseDTO> getGoal(@PathVariable Long id) {
        return savingGoalService.getGoalById(id);
    }

    @PostMapping
    public SavingGoalResponseDTO createGoal(@RequestBody SavingGoalDTO savingGoalDTO) {
        return savingGoalService.createSavingGoal(savingGoalDTO);
    }

    @PutMapping("/{id}/add-savings")
    public void addSavings(@PathVariable Long id, @RequestParam double amount) {
        savingGoalService.addSavings(id, amount);
    }

    @GetMapping("/{id}/total-saved")
    public double getTotalSaved(@PathVariable Long id) {
        return savingGoalService.getTotalSaved(id);
    }

    @GetMapping("/{id}/progress")
    public double getProgress(@PathVariable Long id) {
        return savingGoalService.getProgress(id);
    }
}

