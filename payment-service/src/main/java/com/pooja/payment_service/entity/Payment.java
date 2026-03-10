package com.pooja.payment_service.entity;

import com.pooja.payment_service.dto.PaymentResponse;
import com.pooja.payment_service.enums.PaymentMethod;
import com.pooja.payment_service.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="payments",uniqueConstraints = @UniqueConstraint(columnNames ="idempotencyKey"))
public class Payment {
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Id
@Column (name="id")
private Long id;

@Column(name="order_id")
    private long orderId;

@Column(name="amount")
private double amount;

@Column(name="payment_method")
@Enumerated(EnumType.STRING)
private PaymentMethod paymentMethod;

@Column(name="payment_status")
@Enumerated(EnumType.STRING)
private PaymentStatus paymentStatus;

@Column(name="idempotency_key")
private String idempotencyKey;

@Column(name="created_at",nullable = false,updatable = false)
@CreatedDate
private LocalDateTime createdAt;

@Column(name="updated_at")
@LastModifiedDate
private LocalDateTime updatedAt;

@Column(name="gateway_transaction_id")
    private String gatewayTransactionId;
@Version
private Long version;



}
