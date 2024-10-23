package com.microservice.product_service.Dtos;

import com.microservice.product_service.Models.Product;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryResponse {
    private Long categoryId;
    private String categoryName ;
    private String categoryDescription ;
    private String categoryicon;
    private Date createdAt;
    private Date updatedAt;

}
