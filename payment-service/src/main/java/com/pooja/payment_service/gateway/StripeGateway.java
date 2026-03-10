package com.pooja.payment_service.gateway;

import com.pooja.payment_service.entity.Payment;
import org.springframework.stereotype.Service;

@Service("stripepay")
public class StripeGateway implements PaymentGateway {

    @Override
    public String processPayment(Payment payment) {
        String txnId="STRIPEPAY_TXN_" +System.currentTimeMillis();
        return txnId;
    }
}
