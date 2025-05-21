package com.example.FinanceApp.service;

import com.example.FinanceApp.entity.Recommendation;
import com.example.FinanceApp.entity.base.User;
import com.example.FinanceApp.repository.RecommendationRepository;
import com.example.FinanceApp.template.recommendationTemplate.RecommendationEngine;
import com.example.FinanceApp.service.base.RecommendationService.RecommendationGeneratorServiceInterface;
import org.springframework.stereotype.Service;
import java.util.Optional;

import java.util.List;

@Service
public class RecommendationService implements RecommendationGeneratorServiceInterface {
    private final List<RecommendationEngine> engines;
    private final RecommendationRepository recommendationRepository;

    public RecommendationService(List<RecommendationEngine> engines,
                                 RecommendationRepository recommendationRepository) {
        this.engines = engines;
        this.recommendationRepository = recommendationRepository;
    }

    @Override
    public List<Recommendation> generateAndSaveAll(User user) {
        //Tydzień 9, stream processing 6
        List<Recommendation> results = engines.stream()
                .map(engine -> engine.generateRecommendation(user))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();
        //Koniec, Tydzień 9, stream processing 6
        return recommendationRepository.saveAll(results);
    }
}

