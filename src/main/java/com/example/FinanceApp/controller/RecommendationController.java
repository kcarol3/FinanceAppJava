package com.example.FinanceApp.controller;

import com.example.FinanceApp.entity.Recommendation;
import com.example.FinanceApp.entity.base.User;
import com.example.FinanceApp.service.RecommendationService;
import com.example.FinanceApp.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recommendations")
public class RecommendationController {

    private final RecommendationService recommendationService;
    private final UserService userService;

    public RecommendationController(RecommendationService recommendationService,
                                    UserService userService) {
        this.recommendationService = recommendationService;
        this.userService = userService;
    }

    @PostMapping("/generate")
    public ResponseEntity<List<Recommendation>> generateRecommendations(@RequestParam Long userId) {
        User user = userService.getUserById(userId);
        List<Recommendation> saved = recommendationService.generateAndSaveAll(user);
        return ResponseEntity.ok(saved);
    }

    @GetMapping
    public ResponseEntity<List<Recommendation>> getRecommendations(@RequestParam Long userId) {
        return ResponseEntity.ok(recommendationService.getForUser(userId));
    }
}
