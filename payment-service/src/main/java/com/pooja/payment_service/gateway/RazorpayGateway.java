package com.pooja.payment_service.gateway;

import com.pooja.payment_service.entity.Payment;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service("razorpay")
public class RazorpayGateway implements PaymentGateway {

    @Override
    public String processPayment(Payment payment) {
        // simulate gateway call

        String txnId = "RAZORPAY_TXN_" + UUID.randomUUID().toString();

        return txnId;
    }
}

