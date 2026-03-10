package com.pooja.order_service.mapper;

import com.pooja.order_service.dto.OrderRequest;
import com.pooja.order_service.dto.OrderResponse;
import com.pooja.order_service.entity.Order;
import com.pooja.order_service.enums.OrderStatus;
import lombok.Builder;

import java.time.LocalDateTime;


@Builder

public class OrderMapper {
    public static Order toEntity(OrderRequest request) {
        return Order.builder()
                .productName(request.getProductName())
                .amount(request.getAmount())
                .createdAt(LocalDateTime.now())
           //     .orderStatus(OrderStatus.CREATED)
                .build();


    }
    public static OrderResponse toResponse(Order order) {
        return OrderResponse.builder()
                .id(order.getId())
                .productName(order.getProductName())
                .amount(order.getAmount())
                .orderStatus(order.getOrderStatus())
                .build();
    }

}
