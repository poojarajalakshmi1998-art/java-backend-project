package com.pooja.payment_service.consumer;

import com.pooja.payment_service.common.KafkaTopics;
import com.pooja.payment_service.dto.PaymentRequest;
import com.pooja.payment_service.enums.PaymentMethod;

import com.pooja.payment_service.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Service;
import com.pooja.common_events.event.OrderCreatedEvent;
@Slf4j
@Service
@RequiredArgsConstructor
public class OrderEventConsumer {

private final PaymentService PaymentService;
@RetryableTopic(
        attempts = "2",
        backoff = @Backoff(delay=5000),
        dltTopicSuffix = "-dlt"

)
@KafkaListener(topics =KafkaTopics.ORDER_CREATED,groupId = "payment-group")
    public void consumeOrderCreatedEvent(OrderCreatedEvent event){


    PaymentRequest req = PaymentRequest.builder()
            .orderId(event.getOrderId())
            .amount(event.getAmount())
            .paymentMethod(PaymentMethod.UPI)
            .idempotencyKey(event.getIdempotencyKey())
            .build();

    PaymentService.createpayment(req);

    }


    @KafkaListener(topics =KafkaTopics.ORDER_CREATED+"-dlt",groupId = "payment-group")
    public void consumeDLQOrderCreatedEvent(OrderCreatedEvent event){


        //Log the failure
        //
        //Store failed events for debugging
        //
        //Trigger alerting or manual intervention
        System.out.println("DLT EVENT RECEIVED");

        System.out.println("OrderId : " + event.getOrderId());
        System.out.println("Amount  : " + event.getAmount());
        log.error("Payment processing failed for order {}", event.getOrderId());


    }
}
