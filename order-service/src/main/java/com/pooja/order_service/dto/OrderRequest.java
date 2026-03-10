package com.pooja.order_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class OrderRequest {
@NotBlank(message="Product Name is required")
    private String productName;
@NotNull(message="Amount cannot be null")
@Positive(message = "Amount should be positive")
    private Double amount;
}

