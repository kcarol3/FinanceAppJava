package com.example.FinanceApp.controller;

import com.example.FinanceApp.entity.Recommendation;
import com.example.FinanceApp.entity.base.User;
import com.example.FinanceApp.service.user.UserService;
import com.example.FinanceApp.service.base.RecommendationService.RecommendationGeneratorServiceInterface;
import com.example.FinanceApp.service.base.RecommendationService.RecommendationQueryServiceInterface;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recommendations")
public class RecommendationController {

    private final RecommendationQueryServiceInterface recommendationQueryService;
    private final RecommendationGeneratorServiceInterface recommendationGeneratorService;
    private final UserService userService;

    public RecommendationController(RecommendationQueryServiceInterface recommendationQueryService, RecommendationGeneratorServiceInterface recommendationGeneratorService, UserService userService) {
        this.recommendationQueryService = recommendationQueryService;
        this.recommendationGeneratorService = recommendationGeneratorService;
        this.userService = userService;
    }


    @PostMapping("/generate")
    public ResponseEntity<List<Recommendation>> generateRecommendations(@RequestParam Long userId) {
        User user = userService.getUserById(userId);
        List<Recommendation> saved = recommendationGeneratorService.generateAndSaveAll(user);
        return ResponseEntity.ok(saved);
    }

    @GetMapping
    public ResponseEntity<List<Recommendation>> getRecommendations(@RequestParam Long userId) {
        return ResponseEntity.ok(recommendationQueryService.getForUser(userId));
    }
}
