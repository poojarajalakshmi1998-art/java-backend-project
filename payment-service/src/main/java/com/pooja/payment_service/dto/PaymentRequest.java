package com.pooja.payment_service.dto;

import com.pooja.payment_service.enums.PaymentMethod;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRequest {
    @NotNull(message="OrderID should not be null")
    private Long orderId;
    @NotNull (message="paymentmethod should not be null")
    private PaymentMethod paymentMethod;
    @NotNull(message="amount should not be null")
    @Positive(message="Amount Should be positive")
    private Double amount;
   //  @NotBlank(message="idempotencykey should not be empty or null or blank spaces")
    private String idempotencyKey;
}
