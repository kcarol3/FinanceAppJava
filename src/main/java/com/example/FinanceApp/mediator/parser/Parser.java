package com.example.FinanceApp.mediator.parser;

import com.example.FinanceApp.dto.TransactionDTO;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;

@Service
public abstract class Parser {

    public abstract boolean supports(String contentType);

    public abstract List<TransactionDTO> parse(InputStream inputStream) throws Exception;
}
