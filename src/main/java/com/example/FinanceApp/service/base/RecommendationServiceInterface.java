package com.example.FinanceApp.service.base;

import com.example.FinanceApp.entity.Recommendation;
import com.example.FinanceApp.entity.base.User;

import java.util.List;

//Tydzień 7, ISP 3 ORYGINAŁ
public interface RecommendationServiceInterface {
    List<Recommendation> generateAndSaveAll(User user);
    List<Recommendation> getForUser(Long userId);
}
