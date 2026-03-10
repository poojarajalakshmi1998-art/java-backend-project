package com.pooja.order_service.service;

import com.pooja.order_service.dto.OrderRequest;
import com.pooja.order_service.dto.OrderResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderService {
     OrderResponse createOrder(OrderRequest orderRequest);
    OrderResponse updateOrder(OrderRequest orderRequest,Long orderId);
    OrderResponse cancelOrder(Long orderId);
    Page<OrderResponse> getorders(Pageable pageable);
}
