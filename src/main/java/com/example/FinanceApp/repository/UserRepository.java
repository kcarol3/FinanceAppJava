package com.example.FinanceApp.repository;

import com.example.FinanceApp.entity.base.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
