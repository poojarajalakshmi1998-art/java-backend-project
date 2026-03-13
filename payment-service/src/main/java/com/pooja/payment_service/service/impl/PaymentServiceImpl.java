package com.pooja.payment_service.service.impl;

import com.pooja.payment_service.dto.PaymentRequest;
import com.pooja.payment_service.dto.PaymentResponse;
import com.pooja.payment_service.dto.PaymentStatusUpdateRequest;
import com.pooja.payment_service.entity.Payment;
import com.pooja.payment_service.enums.PaymentStatus;
import com.pooja.payment_service.exception.InvalidPaymentStatusException;
import com.pooja.payment_service.fraud.FraudDetectionService;
import com.pooja.payment_service.gateway.PaymentGateway;
import com.pooja.payment_service.gateway.RazorpayGateway;
import com.pooja.payment_service.mapper.PaymentMapper;
import com.pooja.payment_service.producer.PaymentStatusProducer;
import com.pooja.payment_service.repository.PaymentRepository;
import com.pooja.payment_service.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import com.pooja.common_events.event.PaymentStatusEvent;

import java.util.Map;
import java.util.Optional;

@Slf4j
@Service

public class PaymentServiceImpl implements PaymentService {
 private final PaymentRepository paymentRepo;
 private final FraudDetectionService fraudDetectionService;
 private final PaymentGateway paymentGateway;
 private final PaymentStatusProducer paymentStatusProducer;


    public PaymentServiceImpl(PaymentRepository paymentRepo, FraudDetectionService fraudDetectionService, @Qualifier("razorpay") PaymentGateway paymentGateway,PaymentStatusProducer paymentStatusProducer) {
        this.paymentRepo = paymentRepo;
        this.fraudDetectionService = fraudDetectionService;
        this.paymentGateway = paymentGateway;
        this.paymentStatusProducer = paymentStatusProducer;

    }

@Override
    public PaymentResponse createpayment(PaymentRequest paymentrequest)
    {
        log.info("Received payment request: {}", paymentrequest);
        //idempotency check
        Optional<Payment> existing = paymentRepo.findByIdempotencyKey(paymentrequest.getIdempotencyKey());
        if(existing.isPresent())
        {
            log.info("Idempotent request detected. Returning existing payment");
            return PaymentMapper.toresponse(existing.get());
        }

        //Check existing success payment for the same order id
        Optional<Payment> checkexistingsuccesspayment =paymentRepo.findByOrderIdAndPaymentStatus(paymentrequest.getOrderId(),PaymentStatus.SUCCESS);

        if(checkexistingsuccesspayment.isPresent())
        {
            throw new RuntimeException("Payment already completed for this order");
        }
       Payment payment = PaymentMapper.toentity(paymentrequest);
         //fraud detection check
        if(fraudDetectionService.isfradulent(payment.getAmount()))
        {
            throw new RuntimeException("Fradulent payment detected");
        }
        payment.setPaymentStatus(PaymentStatus.INITIATED);
         paymentRepo.save(payment);
      //call payment Gateway
     String Txnid = paymentGateway.processPayment(payment);
     payment.setGatewayTransactionId(Txnid);
     payment.setPaymentStatus(PaymentStatus.PENDING);
     //update the other details
     Payment savedpayment =paymentRepo.save(payment);
     log.info("Payment saved with ID: {}", payment.getId());
     return PaymentMapper.toresponse(savedpayment);

    }



    @Override
    public PaymentResponse updatepayment(PaymentStatusUpdateRequest  paymentstatusrequest)
    {
        PaymentStatus status;
        Payment payment = paymentRepo.findByGatewayTransactionId(paymentstatusrequest.getGatewayTransactionId()).orElseThrow(()-> new RuntimeException("Payment not found"));
        try {
           status = PaymentStatus.valueOf(paymentstatusrequest.getStatus());

        }
        catch(IllegalArgumentException e)
        {
            throw new InvalidPaymentStatusException(e.getMessage());
        }
        payment.setPaymentStatus(status);
        Payment updated= paymentRepo.save(payment);
        PaymentStatusEvent event = PaymentMapper.toevent(updated);

        paymentStatusProducer.sendPaymentStatusEvent(event);

        return PaymentMapper.toresponse(updated);

    }
    @Override
    public String gettransactionid(Long orderid){
       Payment payment= paymentRepo.
               findByOrderId(orderid).
               orElseThrow(()-> new RuntimeException("Payment not found"));
return payment.getGatewayTransactionId();
    }
}
