package com.example.FinanceApp.command.TransferCommand;

public class TransferInvoker {
    public TransferCommandInterface transferCommandInterface;

    public TransferInvoker(TransferCommandInterface transferCommandInterface) {
        this.transferCommandInterface = transferCommandInterface;
    }

    public void execute() {
        transferCommandInterface.execute();
    }
}
