package com.kit4elite;

import com.kit4elite.entity.Order;
import com.kit4elite.event.OrderCanceledEvent;
import com.kit4elite.event.OrderCreatedEvent;
import com.kit4elite.event.OrderPaidEvent;

import java.util.List;

public interface OrderDomainService {
    OrderCreatedEvent validateAndInitiateOrder(Order order);
    OrderPaidEvent payOrder(Order order);
    void approveOrder(Order order);
    OrderCanceledEvent cancelOrderPayment(Order order , List<String> failureMessages);
    void cancelOrder(Order order, List<String> failureMessages);
}
