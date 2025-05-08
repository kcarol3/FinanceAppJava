package com.example.FinanceApp.command.TransferCommand;

//tydzien 7, dependency inversion 8, wykorzystanie
public class TransferInvoker {
    public TransferCommandInterface transferCommandInterface;

    public TransferInvoker(TransferCommandInterface transferCommandInterface) {
        this.transferCommandInterface = transferCommandInterface;
    }

    public void execute() {
        transferCommandInterface.execute();
    }
}
