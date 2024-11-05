package com.kit4elite.order.service.domain;

import com.kit4elite.order.service.domain.dto.create.CreateOrderCommand;
import com.kit4elite.order.service.domain.dto.create.CreateOrderResponse;
import com.kit4elite.order.service.domain.dto.track.TrackOrderQuery;
import com.kit4elite.order.service.domain.dto.track.TrackOrderResponse;
import com.kit4elite.order.service.domain.ports.input.service.OrderApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Slf4j
@Validated
@Service
class OrderApplicationServiceImpl implements OrderApplicationService {

    public OrderApplicationServiceImpl(OrderCreateCommandHandler orderCreateCommandHandler, OrderTrackCommandHandler orderTrackCommandHandler) {}

    @Override
    public CreateOrderResponse createOrder(CreateOrderCommand createOrderCommand) {
        return null;
    }
    @Override
    public TrackOrderResponse trackOrder(TrackOrderQuery trackOrderQuery) {
        return null;
    }
}
