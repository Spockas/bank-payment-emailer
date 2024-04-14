package com.example.demo.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

@RequiredArgsConstructor
@Log4j2
@Service
public class EmailService {
    private final JavaMailSender emailSender;

    /**
     * Method to send email
     * @param email email address to send email
     * @param body email body in HTML
     * @param subject email subject
     */
    public void sendEmail(String email, String body, String subject) {
        MimeMessage message = createMimeMessage(email, body, subject);
        emailSender.send(message);
    }

    private MimeMessage createMimeMessage(String email, String body, String subject) {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, "utf-8");
        try {
            helper.setTo(email);
            helper.setSubject(subject);
            helper.setText(body, true);
        } catch (MessagingException exception) {
            log.error("Failed to send email to {}", email);
            log.error(exception);
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return message;
    }
}
