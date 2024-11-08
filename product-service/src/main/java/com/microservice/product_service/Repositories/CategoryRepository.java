package com.microservice.product_service.Repositories;

import com.microservice.product_service.Models.Category;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findByIsActiveTrue(Pageable pageable);
}

