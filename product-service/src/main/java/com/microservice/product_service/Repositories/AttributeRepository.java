package com.microservice.product_service.Repositories;

import com.microservice.product_service.Models.Attribute;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttributeRepository extends JpaRepository<Attribute,Long> {
}
