package com.microservice.product_service.Repositories;

import com.microservice.product_service.Models.VariantValues;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VariantValuesRepository extends JpaRepository<VariantValues,Long> {
}
