package com.pooja.common_events.event;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentStatusEvent {

    private Long orderId;
    private String gatewayTransactionId;
    private Double amount;
    private String status;   // SUCCESS or FAILED
    private String reason;
}
