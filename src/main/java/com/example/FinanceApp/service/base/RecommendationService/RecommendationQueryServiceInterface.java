package com.example.FinanceApp.service.base.RecommendationService;

import com.example.FinanceApp.entity.Recommendation;

import java.util.List;

//Tydzień 7, ISP 3
public interface RecommendationQueryServiceInterface {
    List<Recommendation> getForUser(Long userId);
}
//Tydzień 7, ISP 3, koniec