package com.example.FinanceApp.template.recommendationTemplate;

import com.example.FinanceApp.entity.Recommendation;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import com.example.FinanceApp.entity.base.User;

// tydzien 7, dependency inversion 3, impl
@Component
public class AccountRecommendation extends RecommendationEngine {
    private static final Integer MIN_ACCOUNT_NUMBER = 2;

    @Override
    protected boolean isRelevant(User user) {
        return user.getAccounts().stream().count() < MIN_ACCOUNT_NUMBER;
    }

    @Override
    protected Recommendation buildRecommendation(User user) {
        String msg = "Posiadasz tylko jedno konto. Może chciałbyś założyć nowe?";

        Recommendation newRec = new Recommendation();
        newRec.setMessage(msg);
        newRec.setType(RecommendationType.ACCOUNT_SUGGESTION);
        newRec.setCreatedAt(LocalDateTime.now());
        newRec.setUserId(user.getId());
        newRec.setTitle("Rekomendacja kont");

        return newRec;
    }
}

