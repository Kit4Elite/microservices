package com.kit4elite.event;

import com.kit4elite.entity.Order;

import java.time.ZonedDateTime;

public class OrderCanceledEvent extends OrderEvent{
    public OrderCanceledEvent(Order order, ZonedDateTime createAt) {
        super(order, createAt);
    }
}
