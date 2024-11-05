package com.kit4elite.order.service.domain;

import com.kit4elite.OrderDomainService;
import com.kit4elite.entity.Customer;
import com.kit4elite.entity.Order;
import com.kit4elite.event.OrderCreatedEvent;
import com.kit4elite.execption.OrderDomainException;
import com.kit4elite.order.service.domain.dto.create.CreateOrderCommand;
import com.kit4elite.order.service.domain.dto.create.CreateOrderResponse;
import com.kit4elite.order.service.domain.dto.create.OrderItem;
import com.kit4elite.order.service.domain.mapper.OrderMapper;
import com.kit4elite.order.service.domain.ports.output.CustomerRepository;
import com.kit4elite.order.service.domain.ports.output.OrderRepository;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Component
public class OrderCreateCommandHandler {
    private final OrderDomainService orderDomainService;
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final OrderMapper orderMapper;

    public OrderCreateCommandHandler(OrderDomainService orderDomainService, OrderRepository orderRepository, CustomerRepository customerRepository, OrderMapper orderMapper) {
        this.orderDomainService = orderDomainService;
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.orderMapper = orderMapper;
    }

    @Transactional
    public CreateOrderResponse createOrder(CreateOrderCommand createOrderCommand) {
        checkCustomer(createOrderCommand.getCustomerId());
        checkItems(createOrderCommand.getItems());
        Order order = orderMapper.createOrderCommandToOrder(createOrderCommand);
        OrderCreatedEvent orderCreatedEvent = orderDomainService.validateAndInitiateOrder(order);
        Order orderResult = saveOrder(order);
        log.info("Order with id {} saved", order.getId().getValue());
        return orderMapper.mapOrderToCreateOrderResponse(orderResult);
    }


    private void checkItems(@NotNull List<OrderItem> items) {
        //requires to check with product-service
        System.err.println("must implement checkItems which checks with product service");
        log.warn("must implement checkItems which checks with product service");
        return;
    }

    private  void  checkCustomer(@NotNull UUID customerId) {
        Optional<Customer>  customer = customerRepository.findCutomer(customerId);
        if (customer.isEmpty()) {
            log.warn("Customer with id {} not found", customerId);
            throw new OrderDomainException("Customer with id " + customerId + " not found");
        }
    }
    private Order saveOrder(Order order){
        if(orderRepository.save(order) == null) {
            log.error("Could n ot save order with id " + order.getId() );
            throw new OrderDomainException("Could n ot save order with id " + order.getId() );
        }
        log.info("Order with id {} saved", order.getId());
        return order;
    }
}
