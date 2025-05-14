package com.example.FinanceApp.service;

import com.example.FinanceApp.entity.Recommendation;
import com.example.FinanceApp.repository.RecommendationRepository;
import com.example.FinanceApp.service.base.RecommendationService.RecommendationQueryServiceInterface;
import org.springframework.stereotype.Service;

import java.util.List;

//Tydzień 7, ISP 4, uzycie
@Service
public class RecommendationQueryService implements RecommendationQueryServiceInterface {
    private final RecommendationRepository recommendationRepository;

    public RecommendationQueryService(RecommendationRepository recommendationRepository) {
        this.recommendationRepository = recommendationRepository;
    }

    @Override
    public List<Recommendation> getForUser(Long userId) {
        return recommendationRepository.findAllByUserId(userId);
    }
}
