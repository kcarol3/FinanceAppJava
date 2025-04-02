package com.example.FinanceApp.mediator.parser;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public interface ParserMediatorInterface {
    void  sync(MultipartFile file);
}
