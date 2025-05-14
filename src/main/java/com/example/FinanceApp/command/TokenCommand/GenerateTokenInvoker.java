package com.example.FinanceApp.command.TokenCommand;

//tydzien 7, dependency inversion, 7, wykoryztsanie
public class GenerateTokenInvoker {
    public TokenCommandInterface tokenCommandInterface;

    public GenerateTokenInvoker(TokenCommandInterface tokenCommandInterface) {
        this.tokenCommandInterface = tokenCommandInterface;
    }

    public String execute() {
        return tokenCommandInterface.execute();
    }
}
