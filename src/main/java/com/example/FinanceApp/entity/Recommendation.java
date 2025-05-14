package com.example.FinanceApp.entity;

import com.example.FinanceApp.template.recommendationTemplate.RecommendationType;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "recommendations")
public class Recommendation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private String title;
    private String message;

    @Enumerated(EnumType.STRING)
    private RecommendationType type;

    private LocalDateTime createdAt;

    public Recommendation() {
    }

    public Recommendation(Long userId, Recommendation recommendation) {
        this.userId = userId;
        this.title = recommendation.getTitle();
        this.message = recommendation.getMessage();
        this.type = recommendation.getType();
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public RecommendationType getType() {
        return type;
    }

    public void setType(RecommendationType type) {
        this.type = type;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
