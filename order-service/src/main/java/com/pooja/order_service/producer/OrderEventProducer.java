package com.pooja.order_service.producer;

import com.pooja.order_service.common.KafkaTopics;
import com.pooja.order_service.dto.OrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import com.pooja.common_events.event.OrderCreatedEvent;

@Service

public class OrderEventProducer {

private final KafkaTemplate<String,OrderCreatedEvent> kafkatemplate;

public OrderEventProducer(KafkaTemplate<String,OrderCreatedEvent> kafkatemplate)
{

    this.kafkatemplate=kafkatemplate;
}
    public void sendOrderCreatedEvent(OrderCreatedEvent event) {
//event key for Same order events go to same partition

        kafkatemplate.send(KafkaTopics.ORDER_CREATED,event.getOrderId().toString(),event);

    }
}
