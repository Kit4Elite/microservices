package com.microservice.product_service.Repositories;

import com.microservice.product_service.Models.ProductAttributeValues;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductAttributeValuesRepository extends JpaRepository<ProductAttributeValues,Long> {

}
