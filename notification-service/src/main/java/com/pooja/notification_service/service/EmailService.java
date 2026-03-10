package com.pooja.notification_service.service;

import com.pooja.notification_service.entity.NotificationLog;
import com.pooja.notification_service.repository.NotificationLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.pooja.common_events.event.PaymentStatusEvent;
@RequiredArgsConstructor
@Service
public class EmailService {
private final NotificationLogRepository notificationLogRepository;
    public void  SendSuccessEmail(PaymentStatusEvent event) {
        System.out.println("Email sent for order " + event.getOrderId());
NotificationLog log= new NotificationLog();
log.setOrderId(event.getOrderId());
log.setMessage(event.getReason());
log.setStatus(event.getStatus());
log.setGatewayTransactionId(event.getGatewayTransactionId());
log.setAmount(event.getAmount());

        notificationLogRepository.save(log);



    }
    public void  SendFailureEmail(PaymentStatusEvent event) {
        System.out.println("Email sent for order " + event.getOrderId());
        NotificationLog log= new NotificationLog();
        log.setOrderId(event.getOrderId());
        log.setMessage(event.getReason());
        log.setStatus(event.getStatus());
        log.setGatewayTransactionId(event.getGatewayTransactionId());
        log.setAmount(event.getAmount());

        notificationLogRepository.save(log);



    }

}
