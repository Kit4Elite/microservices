package com.microservice.product_service.Repositories;

import com.microservice.product_service.Models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
