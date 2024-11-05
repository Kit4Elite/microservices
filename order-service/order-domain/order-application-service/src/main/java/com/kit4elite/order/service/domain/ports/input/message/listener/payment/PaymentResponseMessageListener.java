package com.kit4elite.order.service.domain.ports.input.message.listener.payment;

import com.kit4elite.order.service.domain.dto.message.PaymentResponse;

public interface PaymentResponseMessageListener {

    void payementComplete(PaymentResponse paymentResponse) ;
    void payementCancelled(PaymentResponse paymentResponse) ;
}
