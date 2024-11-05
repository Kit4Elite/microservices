package com.kit4elite.order.service.domain.ports.output;

import com.kit4elite.entity.Order;
import com.kit4elite.valueObject.TrackingId;

import java.util.Optional;

public interface OrderRepository {

    Order save(Order order);
    Optional<Order> findByTrackingId(TrackingId orderId);
}
