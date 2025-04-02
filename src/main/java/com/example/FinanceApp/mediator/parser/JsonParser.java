package com.example.FinanceApp.mediator.parser;

import com.example.FinanceApp.dto.TransactionDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;

@Service
public class JsonParser extends Parser {
    private final ObjectMapper objectMapper;
    public JsonParser(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public boolean supports(String contentType) {
        return "application/json".equalsIgnoreCase(contentType);
    }

    @Override
    public List<TransactionDTO> parse(InputStream inputStream) throws Exception {
        return objectMapper.readValue(inputStream, new TypeReference<List<TransactionDTO>>() {});
    }
}
