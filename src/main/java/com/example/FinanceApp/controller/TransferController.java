package com.example.FinanceApp.controller;

import com.example.FinanceApp.Command.TransferCommand.TransferCommand;
import com.example.FinanceApp.Command.TransferCommand.TransferCommandInterface;
import com.example.FinanceApp.Command.TransferCommand.TransferInvoker;
import com.example.FinanceApp.dto.TransferDTO;
import com.example.FinanceApp.service.base.AccountServiceInterface;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transfers")
public class TransferController {
    private final AccountServiceInterface accountService;

    private TransferInvoker transferInvoker;

    public TransferController(AccountServiceInterface accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity<String> transfer(@RequestBody TransferDTO transferDTO) {
        TransferCommandInterface command = new TransferCommand(accountService, transferDTO);
        transferInvoker = new TransferInvoker(command);
        transferInvoker.execute();

        return ResponseEntity.ok("Przelew wykonany!");
    }
}
