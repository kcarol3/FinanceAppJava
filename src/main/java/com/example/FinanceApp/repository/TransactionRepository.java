package com.example.FinanceApp.repository;

import com.example.FinanceApp.entity.base.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
