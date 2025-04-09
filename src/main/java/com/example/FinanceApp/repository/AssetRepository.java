package com.example.FinanceApp.repository;

import com.example.FinanceApp.entity.Asset;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssetRepository extends JpaRepository<Asset, Long> {
}

