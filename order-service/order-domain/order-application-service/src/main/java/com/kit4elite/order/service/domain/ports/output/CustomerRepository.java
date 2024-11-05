package com.kit4elite.order.service.domain.ports.output;

import com.kit4elite.entity.Customer;
import com.kit4elite.valueObject.CustomerId;

import java.util.Optional;
import java.util.UUID;

public interface CustomerRepository {
    Optional<Customer>  findCutomer(UUID customerId);
}
