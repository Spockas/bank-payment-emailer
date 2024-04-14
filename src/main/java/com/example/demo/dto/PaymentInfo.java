package com.example.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public final class PaymentInfo {
    @Valid @NotNull
    private final PaymentPerson sender;
    @Valid @NotNull
    private final PaymentPerson receiver;
    @Schema(example = "1")
    @NotNull(message = "documentId is required")
    private final Long documentId;
    @Schema(example = "3.14")
    @NotNull(message = "amount is required")
    private final Double amount;
    @Schema(example = "2024-03-14")
    @NotNull(message = "paymentDate is required")
    private final LocalDate paymentDate;
    @Schema(example = "Processed")
    @NotBlank(message = "status is required")
    private final String status;
    @Schema(example = "-")
    @NotBlank(message = "purpose is required")
    private final String purpose;

}
