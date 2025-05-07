package com.example.FinanceApp.repository;

import com.example.FinanceApp.entity.Recommendation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecommendationRepository extends JpaRepository<Recommendation, Long> {
    List<Recommendation> findAllByUserId(Long userId);
}

