package com.example.FinanceApp.controller.op;

import com.example.FinanceApp.command.TokenCommand.GenerateOtpCommand;
import com.example.FinanceApp.command.TokenCommand.GenerateTokenInvoker;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component("OTP")
public class OtpTokenHandler implements TokenHandler {

    private final Map<String, String> otpStorage = new HashMap<>();

    @Override
    public String generate(String transactionId) {
        String token = new GenerateTokenInvoker(new GenerateOtpCommand(6)).execute();
        otpStorage.put(transactionId, token);
        return token;
    }

    @Override
    public boolean verify(String key, String token) {
        return otpStorage.containsKey(key) && otpStorage.get(key).equals(token);
    }
}
