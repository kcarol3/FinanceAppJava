package com.example.FinanceApp.repository;

import com.example.FinanceApp.memento.UserMemento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMementoRepository extends JpaRepository<UserMemento, Long> {
   UserMemento findTopByUserIdOrderByIdDesc(Long userId);
}
