package com.pooja.order_service.entity;

import com.pooja.order_service.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name="orders")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)

    private Long id;
    @Column(name = "product_name")
    private String productName;
    @Column(name = "amount")
    private Double amount;
    @Column(name = "order_status")
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    @Column(name = "created_at",nullable = false,updatable = false)
    @CreatedDate
    private LocalDateTime createdAt;
    @Column(name="updated_at")
    @LastModifiedDate
     private LocalDateTime updatedAt;
    @Version
    @Column(name="version")
    private Long version;


}


