package com.example.FinanceApp.Command.TokenCommand;

import java.security.SecureRandom;
import java.util.Base64;

public class GenerateResetTokenCommand implements TokenCommandInterface {
    private final int length;

    public GenerateResetTokenCommand(int length) {
        this.length = length;
    }

    @Override
    public String execute() {
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[length];
        random.nextBytes(bytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
    }
}
