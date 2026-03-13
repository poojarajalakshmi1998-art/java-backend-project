package com.pooja.payment_service.service;

import com.pooja.payment_service.dto.PaymentRequest;
import com.pooja.payment_service.dto.PaymentResponse;
import com.pooja.payment_service.dto.PaymentStatusUpdateRequest;

public interface PaymentService {
 PaymentResponse createpayment(PaymentRequest paymentRequest);
 PaymentResponse updatepayment( PaymentStatusUpdateRequest paymentstatusRequest);
 String gettransactionid(Long orderid);
}
