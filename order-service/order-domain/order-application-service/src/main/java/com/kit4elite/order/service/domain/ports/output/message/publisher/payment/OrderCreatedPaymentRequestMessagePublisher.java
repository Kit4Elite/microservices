package com.kit4elite.order.service.domain.ports.output.message.publisher.payment;

import com.kit4elite.event.OrderCreatedEvent;
import com.kit4elite.event.publisher.DomainEventPublisher;

public interface OrderCreatedPaymentRequestMessagePublisher extends DomainEventPublisher<OrderCreatedEvent> {

}
