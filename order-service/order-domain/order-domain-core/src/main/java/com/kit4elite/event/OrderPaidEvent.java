package com.kit4elite.event;

import com.kit4elite.entity.Order;

import java.time.ZonedDateTime;

public class OrderPaidEvent extends OrderEvent{
    public OrderPaidEvent(Order order, ZonedDateTime createAt) {
        super(order, createAt);
    }
}
