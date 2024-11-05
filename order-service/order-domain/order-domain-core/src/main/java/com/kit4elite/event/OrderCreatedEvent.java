package com.kit4elite.event;

import com.kit4elite.entity.Order;

import java.time.ZonedDateTime;

public class OrderCreatedEvent extends OrderEvent{
    public OrderCreatedEvent(Order order, ZonedDateTime createAt) {
        super(order, createAt);
    }
}
