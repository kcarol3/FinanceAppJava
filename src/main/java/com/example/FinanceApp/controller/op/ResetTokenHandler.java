package com.example.FinanceApp.controller.op;

import com.example.FinanceApp.command.TokenCommand.GenerateResetTokenCommand;
import com.example.FinanceApp.command.TokenCommand.GenerateTokenInvoker;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component("RESET")
public class ResetTokenHandler implements TokenHandler {
    private final int RESET_TOKEN_LENGTH = 8;
    private final Map<String, String> resetTokens = new HashMap<>();

    @Override
    public String generate(String userId) {
        String token = new GenerateTokenInvoker(new GenerateResetTokenCommand(RESET_TOKEN_LENGTH)).execute();
        resetTokens.put(userId, token);
        return token;
    }

    @Override
    public boolean verify(String key, String token) {
        return resetTokens.containsKey(key) && resetTokens.get(key).equals(token);
    }
}
