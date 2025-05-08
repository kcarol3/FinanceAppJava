package com.example.FinanceApp.token;

import com.example.FinanceApp.command.TokenCommand.GenerateOtpCommand;
import com.example.FinanceApp.command.TokenCommand.GenerateResetTokenCommand;
import com.example.FinanceApp.command.TokenCommand.GenerateTokenInvoker;
import com.example.FinanceApp.command.TokenCommand.TokenCommandInterface;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

// tydzien 7, liskov 4
@ExtendWith(MockitoExtension.class)
public class GenerateTokenInvokerTest {

    @Mock
    private TokenCommandInterface tokenCommandInterface;

    @InjectMocks
    private GenerateTokenInvoker tokenInvoker;

    @Test
    void shouldFollowLiskovSubstitutionPrinciple() {
        GenerateTokenInvoker resetInvoker = new GenerateTokenInvoker(new GenerateResetTokenCommand(16));
        GenerateTokenInvoker otpInvoker = new GenerateTokenInvoker(new GenerateOtpCommand(6));

        String resetToken = resetInvoker.execute();
        String otp = otpInvoker.execute();

        assertNotNull(resetToken);
        assertNotNull(otp);

        assertEquals(22, resetToken.length());
        assertEquals(6, otp.length());

        assertTrue(resetToken.matches("^[A-Za-z0-9-_]+$"));
        assertTrue(otp.matches("^\\d{6}$"));
    }
}
