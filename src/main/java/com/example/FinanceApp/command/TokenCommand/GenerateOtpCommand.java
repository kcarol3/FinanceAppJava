package com.example.FinanceApp.command.TokenCommand;

import java.security.SecureRandom;

//Tydzien 4, wzorzec Command 2, implementacja jednego z generatorów tokena
public class GenerateOtpCommand implements TokenCommandInterface {
    private final int length;

    public GenerateOtpCommand(int length) {
        this.length = length;
    }

    @Override
    public String execute() {
        SecureRandom random = new SecureRandom();
        StringBuilder otp = new StringBuilder();

        for (int i = 0; i < length; i++) {
            otp.append(random.nextInt(10));
        }

        return otp.toString();
    }
}
//Tydzien 4, wzorzec Command 2, koniec