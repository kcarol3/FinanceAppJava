package com.example.FinanceApp.repository;

import com.example.FinanceApp.entity.AccountGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountGroupRepository extends JpaRepository<AccountGroup, Long> {
    boolean existsAccountGroupByName(String name);
}
