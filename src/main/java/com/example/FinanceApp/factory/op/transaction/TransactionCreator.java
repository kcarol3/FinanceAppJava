package com.example.FinanceApp.factory.op.transaction;

import com.example.FinanceApp.dto.TransactionDTO;
import com.example.FinanceApp.entity.base.Transaction;

public interface TransactionCreator {
    boolean supports(String type);

    Transaction create(TransactionDTO dto);
}
