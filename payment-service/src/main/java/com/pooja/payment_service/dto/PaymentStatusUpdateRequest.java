package com.pooja.payment_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaymentStatusUpdateRequest {
    @NotBlank

private String status;
    @NotBlank
private String gatewayTransactionId;

}
