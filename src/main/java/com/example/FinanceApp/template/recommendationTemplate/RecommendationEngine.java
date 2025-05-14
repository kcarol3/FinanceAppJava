package com.example.FinanceApp.template.recommendationTemplate;

import com.example.FinanceApp.entity.Recommendation;
import com.example.FinanceApp.entity.base.User;

import java.util.Optional;

// Tydzien 5, wzorzec Template  2, abstrakcja silnika rekomendacji
// tydzien 7, dependency inversion 3
public abstract class RecommendationEngine {

    public final Optional<Recommendation> generateRecommendation(User user) {
        if (!isRelevant(user)) {
            return Optional.empty();
        }
        return Optional.of(buildRecommendation(user));
    }

    protected abstract boolean isRelevant(User user);

    protected abstract Recommendation buildRecommendation(User user);
}
// Tydzien 5, wzorzec Template 2, koniec