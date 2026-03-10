package com.pooja.payment_service.fraud;

import org.springframework.stereotype.Service;

@Service
public class FraudDetectionService {

    public boolean isfradulent (double amount)
    {

        if(amount > 1000000)
        {
            return true;
        }
        return false;
    }
}
