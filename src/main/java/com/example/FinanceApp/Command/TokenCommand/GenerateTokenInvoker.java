package com.example.FinanceApp.Command.TokenCommand;

public class GenerateTokenInvoker {
    public TokenCommandInterface tokenCommandInterface;

    public GenerateTokenInvoker(TokenCommandInterface tokenCommandInterface) {
        this.tokenCommandInterface = tokenCommandInterface;
    }

    public String execute() {
        return tokenCommandInterface.execute();
    }
}
