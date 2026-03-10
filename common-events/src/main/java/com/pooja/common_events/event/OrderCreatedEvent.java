package com.pooja.common_events.event;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderCreatedEvent {

    private Long orderId;
    private Double amount;
    private String idempotencyKey;

}