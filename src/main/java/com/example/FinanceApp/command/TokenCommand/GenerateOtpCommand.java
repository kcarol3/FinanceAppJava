package com.example.FinanceApp.command.TokenCommand;

import java.security.SecureRandom;

//Tydzien 4, wzorzec Command 2, implementacja jednego z generatorów tokena
public class GenerateOtpCommand implements TokenCommandInterface {
    private final int length;
    private final int MAX_RANDOM_LENGTH = 10;

    public GenerateOtpCommand(int length) {
        this.length = length;
    }

    @Override
    public String execute() {
        SecureRandom random = new SecureRandom();
        StringBuilder otpToken = new StringBuilder();

        for (int i = 0; i < length; i++) {
            otpToken.append(random.nextInt(MAX_RANDOM_LENGTH));
        }

        return otpToken.toString();
    }
}
//Tydzien 4, wzorzec Command 2, koniec