package com.example.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PaymentPerson{
    @Schema(example = "Some name")
    @NotBlank(message = "name is required")
    private final String name;
    @Schema(example = "XX000000000000000000")
    @NotBlank(message = "iban is required")
    private final String iban;
}
