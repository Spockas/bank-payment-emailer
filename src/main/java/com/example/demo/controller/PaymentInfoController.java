package com.example.demo.controller;

import com.example.demo.dto.PaymentEmail;
import com.example.demo.service.PaymentInfoService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController()
@RequestMapping("/api/v1/email/payment")
public class PaymentInfoController {

    private final PaymentInfoService paymentInfoService;

    @Operation(summary = "Send payment confirmation email",
            description = "Send payment confirmation email to provide email address with provided data")
    @PostMapping
    public ResponseEntity<?> sendEmail(@Valid @RequestBody PaymentEmail paymentEmail) {
        paymentInfoService.sendPaymentInfo(paymentEmail.getPaymentInfo(), paymentEmail.getEmail());
        return ResponseEntity.ok("Email sent successfully");
    }
}
