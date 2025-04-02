package com.example.FinanceApp.repository;

import com.example.FinanceApp.memento.AccountMemento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountMementoRepository extends JpaRepository<AccountMemento, Long> {
    AccountMemento findTopByAccountIdOrderByIdDesc(Long accountId);
}

