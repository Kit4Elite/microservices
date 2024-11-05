package com.kit4elite;

import com.kit4elite.entity.Order;
import com.kit4elite.event.OrderCanceledEvent;
import com.kit4elite.event.OrderCreatedEvent;
import com.kit4elite.event.OrderPaidEvent;
import lombok.extern.slf4j.Slf4j;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

@Slf4j
public class OrderDomainServiceImpl implements OrderDomainService {
    private final String UTC="UTC";

    @Override
    public OrderCreatedEvent validateAndInitiateOrder(Order order) {
        order.validateOrder();
        order.initializeOrder();
        log.info("Initiated order : {}", order.getId().getValue());
        return new OrderCreatedEvent(order, ZonedDateTime.now(ZoneId.of(UTC)));
    }

    @Override
    public OrderPaidEvent payOrder(Order order) {
        order.pay();
        log.info("Order Paid : {}", order.getId().getValue());
        return new OrderPaidEvent(order, ZonedDateTime.now(ZoneId.of(UTC)));
    }

    @Override
    public void approveOrder(Order order) {
        order.approve();
        log.info("Order Approved : {}", order.getId().getValue());
    }

    @Override
    public OrderCanceledEvent cancelOrderPayment(Order order, List<String> failureMessages) {
        order.initCancel(failureMessages);
        log.info("Order cancelling : {}", order.getId().getValue());
        return new OrderCanceledEvent(order, ZonedDateTime.now(ZoneId.of(UTC)));
    }

    @Override
    public void cancelOrder(Order order, List<String> failureMessages) {
        order.cancel();
        log.info("Order cancelled : {}", order.getId().getValue());
    }
}
