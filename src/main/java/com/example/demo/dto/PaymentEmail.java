package com.example.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public final class PaymentEmail {
    @Valid @NotNull
    private final PaymentInfo paymentInfo;
    @Schema(example = "example@example.com")
    @NotBlank @Email(message = "Valid email is required")
    private final String email;

}
