package com.microservice.product_service.Mappers;

import com.microservice.product_service.Dtos.CategoryFullResponse;
import com.microservice.product_service.Dtos.CategoryResponse;
import com.microservice.product_service.Models.Category;

public class CategoryMapper {
    public static CategoryResponse mapToCategoryResponse(Category category) {
        return CategoryResponse.builder()
                .categoryId(category.getCategoryId())
                .categoryName(category.getCategoryName())
                .categoryDescription(category.getCategoryDescription())
                .categoryicon(category.getCategoryicon())
                .createdAt(category.getCreatedAt())
                .updatedAt(category.getUpdatedAt())
                .build();
    }
    public static CategoryFullResponse mapToCategoryFullResponse(Category category) {
        return CategoryFullResponse.builder()
                .categoryId(category.getCategoryId())
                .categoryName(category.getCategoryName())
                .categoryDescription(category.getCategoryDescription())
                .categoryicon(category.getCategoryicon())
                .products(!category.getProducts().isEmpty() ? category.getProducts().stream().map(ProductMapper::mapToShortProductDTO).toList() : null)
                .createdAt(category.getCreatedAt())
                .updatedAt(category.getUpdatedAt())
                .build();
    }
}
