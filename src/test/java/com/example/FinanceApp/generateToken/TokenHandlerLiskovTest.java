package com.example.FinanceApp.generateToken;

import static org.junit.jupiter.api.Assertions.*;

import com.example.FinanceApp.controller.op.OtpTokenHandler;
import com.example.FinanceApp.controller.op.ResetTokenHandler;
import com.example.FinanceApp.controller.op.TokenHandler;
import org.junit.jupiter.api.Test;
import java.util.UUID;

// tydzien 7, liskov 6
public class TokenHandlerLiskovTest {

    private TokenHandler getTokenHandler(String type) {
        return switch (type) {
            case "RESET" -> new ResetTokenHandler();
            case "OTP" -> new OtpTokenHandler();
            default -> throw new IllegalArgumentException("Unknown handler type");
        };
    }

    @Test
    void testGenerateAndVerifyResetToken() {
        TokenHandler handler = getTokenHandler("RESET");
        String userId = UUID.randomUUID().toString();
        String token = handler.generate(userId);
        assertNotNull(token);
        assertTrue(handler.verify(userId, token));
    }

    @Test
    void testGenerateAndVerifyOtpToken() {
        TokenHandler handler = getTokenHandler("OTP");
        String transactionId = UUID.randomUUID().toString();
        String token = handler.generate(transactionId);
        assertNotNull(token);
        assertTrue(handler.verify(transactionId, token));
    }

    @Test
    void testLiskovSubstitution() {
        TokenHandler resetHandler = getTokenHandler("RESET");
        TokenHandler otpHandler = getTokenHandler("OTP");

        String id1 = UUID.randomUUID().toString();
        String token1 = resetHandler.generate(id1);
        assertTrue(resetHandler.verify(id1, token1));

        String id2 = UUID.randomUUID().toString();
        String token2 = otpHandler.generate(id2);
        assertTrue(otpHandler.verify(id2, token2));

        // Assert interchangeability (LSP)
        assertTrue((resetHandler).verify(id1, token1));
        assertTrue((otpHandler).verify(id2, token2));
    }
}
