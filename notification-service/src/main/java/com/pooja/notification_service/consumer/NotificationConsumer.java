package com.pooja.notification_service.consumer;

import com.pooja.common_events.event.PaymentStatusEvent;
import com.pooja.notification_service.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificationConsumer {

private final EmailService emailService;


    @KafkaListener(
            topics = {"payment-success", "payment-failure"},
            groupId = "notification-group"
    )
    public void handlePaymentEvent(PaymentStatusEvent event){

        if(event.getStatus().equalsIgnoreCase("SUCCESS")){
            emailService.SendSuccessEmail(event);
        } else {
            emailService.SendFailureEmail(event);
        }
    }
}
