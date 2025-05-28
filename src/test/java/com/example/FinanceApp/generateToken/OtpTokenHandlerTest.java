package com.example.FinanceApp.generateToken;


import com.example.FinanceApp.controller.op.OtpTokenHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class OtpTokenHandlerTest {

    private OtpTokenHandler otpTokenHandler;

    @BeforeEach
    public void setUp() {
        otpTokenHandler = new OtpTokenHandler();
    }

    @Test
    public void testGenerateReturnsNonNullToken() {
        String transactionId = "txn123";
        String token = otpTokenHandler.generate(transactionId);
        assertNotNull(token, "Generated token should not be null");
    }

    @Test
    public void testVerifyReturnsTrueForValidToken() {
        String transactionId = "txn456";
        String token = otpTokenHandler.generate(transactionId);
        boolean result = otpTokenHandler.verify(transactionId, token);
        assertTrue(result, "Verification should return true for valid token");
    }

    @Test
    public void testVerifyReturnsFalseForInvalidToken() {
        String transactionId = "txn789";
        otpTokenHandler.generate(transactionId);
        boolean result = otpTokenHandler.verify(transactionId, "wrongToken");
        assertFalse(result, "Verification should return false for invalid token");
    }

    @Test
    public void testVerifyReturnsFalseForUnknownTransactionId() {
        boolean result = otpTokenHandler.verify("unknownTxn", "anyToken");
        assertFalse(result, "Verification should return false for unknown transaction ID");
    }

    @Test
    public void testGeneratedTokenHasCorrectLength() {
        String transactionId = "txn101";
        String token = otpTokenHandler.generate(transactionId);
        assertEquals(8, token.length(), "Generated OTP should have length 8");
    }
}

