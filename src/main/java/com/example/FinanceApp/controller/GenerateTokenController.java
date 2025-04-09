package com.example.FinanceApp.controller;

import com.example.FinanceApp.command.TokenCommand.GenerateOtpCommand;
import com.example.FinanceApp.command.TokenCommand.GenerateResetTokenCommand;
import com.example.FinanceApp.command.TokenCommand.GenerateTokenInvoker;
import com.example.FinanceApp.command.TokenCommand.TokenCommandInterface;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/token")
public class GenerateTokenController {
    private final Map<String, String> resetTokens = new HashMap<>();
    private final Map<String, String> otpStorage = new HashMap<>();

    private GenerateTokenInvoker generateTokenInvoker;

    @PostMapping("/generate")
    public ResponseEntity<String> generateToken(@RequestParam String type, @RequestParam(required = false) String userId, @RequestParam(required = false) String transactionId) {
        TokenCommandInterface command;
        String token;

        switch (type.toUpperCase()) {
            case "OTP":
                command = new GenerateOtpCommand(6);
                generateTokenInvoker = new GenerateTokenInvoker(command);
                token = generateTokenInvoker.execute();
                otpStorage.put(transactionId, token);
                break;

            case "RESET":
                command = new GenerateResetTokenCommand(16);
                generateTokenInvoker = new GenerateTokenInvoker(command);
                token = generateTokenInvoker.execute();
                resetTokens.put(userId, token);
                break;

            default:
                return ResponseEntity.badRequest().body("Nieznany typ tokena: " + type);
        }

        return ResponseEntity.ok("Token: " + token);
    }

    @PostMapping("/verify")
    public ResponseEntity<String> verify(@RequestParam String type, @RequestParam String key, @RequestParam String token) {
        switch (type.toUpperCase()) {
            case "OTP":
                if (otpStorage.containsKey(key) && otpStorage.get(key).equals(token)) {
                    otpStorage.remove(key);
                    return ResponseEntity.ok("Token accepted");
                }
                break;

            case "RESET":
                if (resetTokens.containsKey(key) && resetTokens.get(key).equals(token)) {
                    resetTokens.remove(key);
                    return ResponseEntity.ok("Token accepted");
                }
                break;

            default:
                return ResponseEntity.badRequest().body("Nieznany typ tokena: " + type);
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Niepoprawny token");
    }
}
