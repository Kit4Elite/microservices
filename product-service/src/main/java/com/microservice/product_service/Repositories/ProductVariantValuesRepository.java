package com.microservice.product_service.Repositories;

import com.microservice.product_service.Models.ProductVariantValues;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductVariantValuesRepository extends JpaRepository<ProductVariantValues,Long> {
}
