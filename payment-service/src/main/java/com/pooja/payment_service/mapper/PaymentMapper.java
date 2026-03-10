package com.pooja.payment_service.mapper;

import com.pooja.common_events.event.PaymentStatusEvent;
import com.pooja.payment_service.dto.PaymentRequest;
import com.pooja.payment_service.dto.PaymentResponse;
import com.pooja.payment_service.entity.Payment;
import com.pooja.payment_service.enums.PaymentMethod;
import com.pooja.payment_service.enums.PaymentStatus;

import java.time.LocalDateTime;

public class PaymentMapper {

    public static Payment toentity(PaymentRequest paymentrequest)
    {
        Payment payment=Payment.builder().orderId(paymentrequest.getOrderId())
                      .paymentMethod(paymentrequest.getPaymentMethod())
                                .amount(paymentrequest.getAmount())
                .idempotencyKey(paymentrequest.getIdempotencyKey()). build();
               return payment;

    }

    public static PaymentResponse toresponse(Payment payment)
    {
        PaymentResponse response=PaymentResponse.builder().paymentId(payment.getId())
                .orderId(payment.getOrderId())
                .amount(payment.getAmount())
                .paymentStatus(payment.getPaymentStatus())
                 .paymentMethod(payment.getPaymentMethod())
                  .idempotencyKey(payment.getIdempotencyKey())
                .gatewayTransactionId(payment.getGatewayTransactionId())
                        .createdAt(payment.getCreatedAt()).build();
        return response;

    }

    public static PaymentStatusEvent toevent(Payment payment)
    {
        PaymentStatusEvent event = PaymentStatusEvent.builder()
        .orderId(payment.getOrderId())
            .gatewayTransactionId(payment.getGatewayTransactionId())
            .amount(payment.getAmount())
            .status(payment.getPaymentStatus().toString())
            .reason("").build();
return event;
    }
}
