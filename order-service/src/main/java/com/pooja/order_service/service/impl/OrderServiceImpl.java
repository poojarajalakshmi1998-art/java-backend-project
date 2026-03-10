package com.pooja.order_service.service.impl;

import com.pooja.order_service.dto.OrderRequest;
import com.pooja.order_service.dto.OrderResponse;
import com.pooja.order_service.entity.Order;
import com.pooja.order_service.enums.OrderStatus;
import com.pooja.common_events.event.OrderCreatedEvent;
import com.pooja.order_service.mapper.OrderMapper;
import com.pooja.order_service.producer.OrderEventProducer;
import com.pooja.order_service.repository.OrderRepository;
import com.pooja.order_service.service.OrderService;
import jakarta.validation.constraints.Null;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.antlr.v4.runtime.tree.xpath.XPath.findAll;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepo;
    private final OrderEventProducer orderEventProducer;

    private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);
    @Override
    public OrderResponse createOrder(OrderRequest orderRequest) {

       Order order= OrderMapper.toEntity(orderRequest);
       order.setOrderStatus(OrderStatus.CREATED);
        Order saved=orderRepo.save(order);


        OrderCreatedEvent orderCreatedEvent = new OrderCreatedEvent(  saved.getId(),
                saved.getAmount(),
                saved.getId().toString());
        //Kafka order-created topic call
        orderEventProducer.sendOrderCreatedEvent(orderCreatedEvent);

        OrderResponse orderResponse=OrderMapper.toResponse(saved);
        logger.info("Order saved successfully for the Product : "+orderResponse.getProductName());

        return orderResponse;
    }
@Override
   public  OrderResponse updateOrder(OrderRequest orderRequest,Long orderId)

{


    Order order= orderRepo.findById(orderId).orElseThrow(()->new RuntimeException("Product not found"));
    //if(notnull(orderRequest.getAmount()) && !("".equalsIgnoreCase(orderRequest.getAmount())))
    if(orderRequest.getAmount() != null) {

        order.setAmount(orderRequest.getAmount());
    }

    if(orderRequest.getProductName() != null) {
        order.setProductName(orderRequest.getProductName());
    }
    Order updated=orderRepo.save(order);
    logger.info("Order detail updated for the Product : "+updated.getProductName());
         return OrderMapper.toResponse(updated);
}


public OrderResponse  cancelOrder(Long orderId)
{

Order order= orderRepo.findById(orderId).orElseThrow(()->new RuntimeException("Product not found"));

if(order.getOrderStatus()==OrderStatus.SHIPPED && order.getOrderStatus()==OrderStatus.DELIVERED)
    throw new IllegalStateException("Cannot cancel shipped/delivered order");

    order.setOrderStatus(OrderStatus.CANCELLED);
    Order saved = orderRepo.save(order);
    OrderResponse orderResponse = OrderMapper.toResponse(saved);
    logger.info("Order cancelled for the Product : "+orderResponse.getProductName());

return orderResponse;


}


public Page<OrderResponse> getorders(Pageable pageable)
{
  // Pageable pageable= PageRequest.of(page,size);
   Page<OrderResponse> order=orderRepo.findAll(pageable).map(OrderMapper::toResponse);
    logger.info("Order details fetched for the Products");
   return order;

}
}
