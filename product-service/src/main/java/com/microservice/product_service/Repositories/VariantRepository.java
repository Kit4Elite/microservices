package com.microservice.product_service.Repositories;

import com.microservice.product_service.Models.Variant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VariantRepository extends JpaRepository<Variant,Long> {
}
