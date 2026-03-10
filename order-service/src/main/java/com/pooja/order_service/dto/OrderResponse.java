package com.pooja.order_service.dto;

import com.pooja.order_service.enums.OrderStatus;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Builder
@Data
public class OrderResponse {
    private Long id;
    private String productName;
    private Double amount;
    private OrderStatus orderStatus;

}
