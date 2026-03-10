package com.pooja.notification_service.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "notification_logs")
public class NotificationLog {
    @Id
private String id;
    private Long OrderId;
    private String gatewayTransactionId;


    private String transactionId;

    private String status;

    private String message;
    private double amount;

   // @Indexed(expireAfterSeconds = 604800)
    private LocalDateTime createdAt;
}


