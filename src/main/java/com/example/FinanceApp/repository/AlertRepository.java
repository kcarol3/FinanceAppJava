package com.example.FinanceApp.repository;

import com.example.FinanceApp.entity.Alert;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlertRepository extends JpaRepository<Alert, Long> {}
