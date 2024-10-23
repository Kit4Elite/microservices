package com.microservice.product_service.Repositories;

import com.microservice.product_service.Models.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {

}
