package com.pooja.payment_service.gateway;


import com.pooja.payment_service.entity.Payment;

public interface PaymentGateway {

    String processPayment (Payment payment);
}
