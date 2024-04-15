package com.example.demo.service;

import jakarta.mail.internet.MimeMessage;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import static org.mockito.Mockito.*;

public class EmailServiceTest {
    @Test
    public void testSendEmail_Successful() throws Exception {
        String email = "test@example.com";
        String body = "<html><body>Hello world!</body></html>";
        String subject = "Test Subject";

        JavaMailSender mailSender = mock(JavaMailSender.class);
        MimeMessage mimeMessage = mock(MimeMessage.class);

        when(mailSender.createMimeMessage()).thenReturn(mimeMessage);

        EmailService emailService = new EmailService(mailSender);
        try (MockedConstruction<MimeMessageHelper> mockedHelper = mockConstruction(MimeMessageHelper.class)) {
            emailService.sendEmail(email, body, subject);

            MimeMessageHelper mimeMessageHelper = mockedHelper.constructed().get(0);

            verify(mimeMessageHelper).setTo(email);
            verify(mimeMessageHelper).setSubject(subject);
            verify(mimeMessageHelper).setText(body, true);
            verify(mailSender).send(mimeMessage);
        }
    }
}