package com.kit4elite.order.service.domain.ports.input.service;


import com.kit4elite.order.service.domain.dto.create.CreateOrderCommand;
import com.kit4elite.order.service.domain.dto.create.CreateOrderResponse;
import com.kit4elite.order.service.domain.dto.track.TrackOrderQuery;
import com.kit4elite.order.service.domain.dto.track.TrackOrderResponse;
import jakarta.validation.Valid;

public interface OrderApplicationService {

    CreateOrderResponse createOrder(@Valid CreateOrderCommand createOrderCommand);
    TrackOrderResponse trackOrder(@Valid TrackOrderQuery trackOrderQuery);
}
