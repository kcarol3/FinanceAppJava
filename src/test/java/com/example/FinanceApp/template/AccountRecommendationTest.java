package com.example.FinanceApp.template;

import com.example.FinanceApp.entity.Recommendation;
import com.example.FinanceApp.entity.SavingsAccount;
import com.example.FinanceApp.entity.base.User;
import com.example.FinanceApp.template.recommendationTemplate.AccountRecommendation;
import com.example.FinanceApp.template.recommendationTemplate.RecommendationType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountRecommendationTest {

    private final AccountRecommendation accountRecommendation = new AccountRecommendation();

    @Test
    void testIsRelevant_UserWithNoAccounts_ShouldReturnTrue() {
        User user = new User();

        assertTrue(accountRecommendation.isRelevant(user));
    }

    @Test
    void testIsRelevant_UserWithOneAccount_ShouldReturnTrue() {
        User user = new User();
        user.addAccount(new SavingsAccount()); // 1 konto

        assertTrue(accountRecommendation.isRelevant(user));
    }

    @Test
    void testIsRelevant_UserWithTwoAccounts_ShouldReturnFalse() {
        User user = new User();
        user.addAccount(new SavingsAccount());
        user.addAccount(new SavingsAccount());

        assertFalse(accountRecommendation.isRelevant(user));
    }

    @Test
    void testBuildRecommendation_ShouldReturnProperRecommendation() {
        User user = new User();
        user.setId(123L);

        Recommendation recommendation = accountRecommendation.buildRecommendation(user);

        assertEquals("Posiadasz tylko jedno konto. Może chciałbyś założyć nowe?", recommendation.getMessage());
        assertEquals(RecommendationType.ACCOUNT_SUGGESTION, recommendation.getType());
        assertEquals("Rekomendacja kont", recommendation.getTitle());
        assertEquals(123L, recommendation.getUserId());
        assertNotNull(recommendation.getCreatedAt());
    }

    @Test
    void testIsRelevant_UserWithNullAccounts_ShouldReturnTrue() {
        User user = new User();
        user.addAccount(null); // przypadek brzegowy

        // Jeżeli metoda nie radzi sobie z null – ten test się wywali i trzeba poprawić klasę
        boolean result = false;
        try {
            result = accountRecommendation.isRelevant(user);
        } catch (Exception e) {
            fail("Method should handle null account list without throwing an exception.");
        }

        assertTrue(result);
    }
}
