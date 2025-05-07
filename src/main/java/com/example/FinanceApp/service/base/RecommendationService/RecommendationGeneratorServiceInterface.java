package com.example.FinanceApp.service.base.RecommendationService;

import com.example.FinanceApp.entity.Recommendation;
import com.example.FinanceApp.entity.base.User;

import java.util.List;

//Tydzień 7, ISP 3
public interface RecommendationGeneratorServiceInterface {
    List<Recommendation> generateAndSaveAll(User user);
}
//Tydzień 7, ISP 3, koniec