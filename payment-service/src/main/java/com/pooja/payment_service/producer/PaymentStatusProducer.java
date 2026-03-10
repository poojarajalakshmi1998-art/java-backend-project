package com.pooja.payment_service.producer;


import com.pooja.common_events.event.PaymentStatusEvent;
import com.pooja.payment_service.enums.PaymentStatus;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;


@Component
public class PaymentStatusProducer {

    private final KafkaTemplate<String,Object> kafkaTemplate;
    public PaymentStatusProducer(KafkaTemplate<String,Object> kafkaTemplate) {

        this.kafkaTemplate= kafkaTemplate;
    }

    public void sendPaymentStatusEvent(PaymentStatusEvent paymentStatusEvent) {
        if (paymentStatusEvent.getStatus().equalsIgnoreCase("SUCCESS")) {

            PaymentStatusEvent event = new PaymentStatusEvent(
                    paymentStatusEvent.getOrderId(),
                    paymentStatusEvent.getGatewayTransactionId(),
                    paymentStatusEvent.getAmount(),
                    "SUCCESS",
                    "TRANSACTION COMPLETED"
            );

            kafkaTemplate.send("payment-success", event.getOrderId().toString(), event);

        }

        if (paymentStatusEvent.getStatus().equalsIgnoreCase("FAILED")) {

            PaymentStatusEvent event = new PaymentStatusEvent(
                    paymentStatusEvent.getOrderId(),
                    paymentStatusEvent.getGatewayTransactionId(),
                    paymentStatusEvent.getAmount(),
                    "FAILURE",
                    "TRANSACTION FAILED"

            );

            kafkaTemplate.send("payment-failure", event.getOrderId().toString(), event);

        }
    }
}
