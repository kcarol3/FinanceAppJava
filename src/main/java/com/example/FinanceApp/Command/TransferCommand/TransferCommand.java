package com.example.FinanceApp.Command.TransferCommand;

import com.example.FinanceApp.dto.TransferDTO;
import com.example.FinanceApp.service.base.AccountServiceInterface;

//Tydzień 4, Wzorzec Command 1, implementacja interfejsu command, umożliwia transfer środków między kontami

public class TransferCommand implements TransferCommandInterface {
    private final AccountServiceInterface accountService;

    private final TransferDTO transferDTO;

    public TransferCommand(AccountServiceInterface accountService, TransferDTO transferDTO) {
        this.accountService = accountService;
        this.transferDTO = transferDTO;
    }

    @Override
    public void execute() {
        accountService.transferMoney(transferDTO.getFromAccountId(), transferDTO.getToAccountId(), transferDTO.getAmount());
    }
}
//Tydzien 4, Wzorzec command 1, koniec
