package com.example.FinanceApp.template.recomendationTemplate;

import com.example.FinanceApp.entity.Recommendation;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.example.FinanceApp.entity.base.User;

// tydzien 7, dependency inversion 3, impl
@Component
public class AccountRecomendation extends RecommendationEngine {
    @Override
    protected boolean isRelevant(User user) {
        return user.getAccounts().stream().count() < 2;
    }

    @Override
    protected Recommendation buildRecommendation(User user) {
        String msg = "Posiadasz tylko jedno konto. Może Chciałbyś założyć nowe?";

        Recommendation newRec = new Recommendation();
        newRec.setMessage(msg);
        newRec.setType(RecommendationType.ACCOUNT_SUGGESTION);
        newRec.setCreatedAt(LocalDateTime.now());
        newRec.setUserId(user.getId());
        newRec.setTitle("Rekomendacja kont");

        return newRec;
    }
}

