package com.pooja.payment_service.repository;


import com.pooja.payment_service.entity.Payment;
import com.pooja.payment_service.enums.PaymentStatus;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.domain.Limit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment,Long> {


    Optional<Payment> findByIdempotencyKey(String idempotencyKey);

    Optional<Payment> findByOrderIdAndPaymentStatus(Long orderId, PaymentStatus paymentStatus);


    Optional<Payment> findByGatewayTransactionId( String gatewayTransactionId);
}
