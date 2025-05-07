package com.example.FinanceApp.controller;

import com.example.FinanceApp.command.TokenCommand.GenerateOtpCommand;
import com.example.FinanceApp.command.TokenCommand.GenerateResetTokenCommand;
import com.example.FinanceApp.command.TokenCommand.GenerateTokenInvoker;
import com.example.FinanceApp.command.TokenCommand.TokenCommandInterface;
import com.example.FinanceApp.controller.op.TokenHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiPredicate;
import java.util.function.Function;

//Tydzien 6, Open-closed, 6
@RestController
@RequestMapping("/token")
public class GenerateTokenController {
    // oryginal
//    private final Map<String, String> resetTokens = new HashMap<>();
//    private final Map<String, String> otpStorage = new HashMap<>();
//
//    private GenerateTokenInvoker generateTokenInvoker;
//
//    @PostMapping("/generate")
//    public ResponseEntity<String> generateToken(@RequestParam String type, @RequestParam(required = false) String userId, @RequestParam(required = false) String transactionId) {
//        TokenCommandInterface command;
//        String token;
//
//        switch (type.toUpperCase()) {
//            case "OTP":
//                command = new GenerateOtpCommand(6);
//                generateTokenInvoker = new GenerateTokenInvoker(command);
//                token = generateTokenInvoker.execute();
//                otpStorage.put(transactionId, token);
//                break;
//
//            case "RESET":
//                command = new GenerateResetTokenCommand(16);
//                generateTokenInvoker = new GenerateTokenInvoker(command);
//                token = generateTokenInvoker.execute();
//                resetTokens.put(userId, token);
//                break;
//
//            default:
//                return ResponseEntity.badRequest().body("Nieznany typ tokena: " + type);
//        }
//
//        return ResponseEntity.ok("Token: " + token);
//    }
//
//    @PostMapping("/verify")
//    public ResponseEntity<String> verify(@RequestParam String type, @RequestParam String key, @RequestParam String token) {
//        switch (type.toUpperCase()) {
//            case "OTP":
//                if (otpStorage.containsKey(key) && otpStorage.get(key).equals(token)) {
//                    otpStorage.remove(key);
//                    return ResponseEntity.ok("Token accepted");
//                }
//                break;
//
//            case "RESET":
//                if (resetTokens.containsKey(key) && resetTokens.get(key).equals(token)) {
//                    resetTokens.remove(key);
//                    return ResponseEntity.ok("Token accepted");
//                }
//                break;
//
//            default:
//                return ResponseEntity.badRequest().body("Nieznany typ tokena: " + type);
//        }
//
//        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Niepoprawny token");
//    }

    //abstrakcja
//    private final Map<String, TokenHandler> handlers;
//
//    public GenerateTokenController(Map<String, TokenHandler> handlers) {
//        this.handlers = handlers;
//    }
//
//    @PostMapping("/generate")
//    public ResponseEntity<String> generateToken(@RequestParam String type, @RequestParam(required = false) String userId, @RequestParam(required = false) String transactionId) {
//        TokenHandler handler = handlers.get(type.toUpperCase());
//        if (handler == null) {
//            return ResponseEntity.badRequest().body("Nieznany typ tokena: " + type);
//        }
//
//        String token = handler.generate(type.equalsIgnoreCase("OTP") ? transactionId : userId);
//        return ResponseEntity.ok("Token: " + token);
//    }
//
//    @PostMapping("/verify")
//    public ResponseEntity<String> verify(@RequestParam String type, @RequestParam String key, @RequestParam String token) {
//        TokenHandler handler = handlers.get(type.toUpperCase());
//        if (handler == null) {
//            return ResponseEntity.badRequest().body("Nieznany typ tokena: " + type);
//        }
//
//        if (handler.verify(key, token)) {
//            return ResponseEntity.ok("Token accepted");
//        }
//
//        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Niepoprawny token");
//    }

    //sterowanie danymi
    private final Map<String, String> resetTokens = new HashMap<>();
    private final Map<String, String> otpStorage = new HashMap<>();

    private final Map<String, Function<String, String>> tokenGenerators = Map.of(
            "OTP", transactionId -> {
                String token = new GenerateTokenInvoker(new GenerateOtpCommand(6)).execute();
                otpStorage.put(transactionId, token);
                return token;
            },
            "RESET", userId -> {
                String token = new GenerateTokenInvoker(new GenerateResetTokenCommand(16)).execute();
                resetTokens.put(userId, token);
                return token;
            }
    );

    private final Map<String, BiPredicate<String, String>> tokenVerifiers = Map.of(
            "OTP", (key, token) -> token.equals(otpStorage.get(key)),
            "RESET", (key, token) -> token.equals(resetTokens.get(key))
    );

    @PostMapping("/generate")
    public ResponseEntity<String> generateToken(@RequestParam String type, @RequestParam(required = false) String userId, @RequestParam(required = false) String transactionId) {
        Function<String, String> generator = tokenGenerators.get(type.toUpperCase());
        if (generator == null) {
            return ResponseEntity.badRequest().body("Nieznany typ tokena: " + type);
        }

        String key = type.equalsIgnoreCase("OTP") ? transactionId : userId;
        String token = generator.apply(key);

        return ResponseEntity.ok("Token: " + token);
    }

    @PostMapping("/verify")
    public ResponseEntity<String> verify(@RequestParam String type, @RequestParam String key, @RequestParam String token) {
        BiPredicate<String, String> verifier = tokenVerifiers.get(type.toUpperCase());
        if (verifier == null) {
            return ResponseEntity.badRequest().body("Nieznany typ tokena: " + type);
        }

        if (verifier.test(key, token)) {
            return ResponseEntity.ok("Token accepted");
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Niepoprawny token");
    }
}

//Tydzien 6, Open-closed, 6, koniec
