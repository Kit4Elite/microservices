package com.kit4elite.order.service.domain.mapper;


import com.kit4elite.entity.Order;
import com.kit4elite.entity.OrderItem;
import com.kit4elite.entity.Product;
import com.kit4elite.order.service.domain.dto.create.CreateOrderCommand;
import com.kit4elite.order.service.domain.dto.create.CreateOrderResponse;
import com.kit4elite.order.service.domain.dto.create.OrderAddress;
import com.kit4elite.valueObject.CustomerId;
import com.kit4elite.valueObject.Money;
import com.kit4elite.valueObject.ProductId;
import com.kit4elite.valueObject.StreetAddress;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class OrderMapper {
    public  Order createOrderCommandToOrder(CreateOrderCommand createOrderCommand){
        return Order.builder()
                .customerId(new CustomerId(createOrderCommand.getCustomerId()))
                .streetAddress(mapAddressToStreetAddress(createOrderCommand.getAddress()))
                .price(new Money(createOrderCommand.getPrice()))
                .orderItems(mapOrderItemsToOrderItemsEntity(createOrderCommand.getItems()))
                .build();
    }

    private List<OrderItem> mapOrderItemsToOrderItemsEntity(@NotNull List<com.kit4elite.order.service.domain.dto.create.OrderItem> items) {
        return items.stream().map((orderItem -> OrderItem.builder()
                .product(new Product(new ProductId(orderItem.getProductId())))
                .quantity(orderItem.getQuantity())
                .subTotal(new Money(orderItem.getSubTotal()))
                .build())).toList();
    }

    private StreetAddress mapAddressToStreetAddress(@NotNull OrderAddress address) {
        return new StreetAddress(UUID.randomUUID()
                , address.getStreet()
                , address.getCity()
                , address.getPostalcode()
                , address.getCountry());
    }

    public CreateOrderResponse mapOrderToCreateOrderResponse(Order order ){
        return CreateOrderResponse
                .builder()
                .orderTrackingId(order.getTrackingId().getValue())
                .orderStatus(order.getOrderStatus())
                .build();
    }
}
