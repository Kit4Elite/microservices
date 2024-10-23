package com.microservice.product_service.Repositories;

import com.microservice.product_service.Models.AttributeValues;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttributeValuesRepository extends JpaRepository<AttributeValues,Long> {
}
