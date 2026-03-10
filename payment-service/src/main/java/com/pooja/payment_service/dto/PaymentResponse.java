package com.pooja.payment_service.dto;

import com.pooja.payment_service.enums.PaymentMethod;
import com.pooja.payment_service.enums.PaymentStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class PaymentResponse {
    private Long paymentId;

    private Long orderId;

    private Double amount;

    private PaymentStatus paymentStatus;


    private PaymentMethod paymentMethod;

    private String idempotencyKey;
    private String gatewayTransactionId;
    private LocalDateTime createdAt;


}
