package com.microservice.product_service.Services;

import com.microservice.product_service.Dtos.CategoryFullResponse;
import com.microservice.product_service.Dtos.CategoryRequest;
import com.microservice.product_service.Dtos.CategoryResponse;
import com.microservice.product_service.Dtos.ShortProductResponse;
import com.microservice.product_service.Mappers.CategoryMapper;
import com.microservice.product_service.Mappers.ProductMapper;
import com.microservice.product_service.Models.Category;
import com.microservice.product_service.Models.Product;
import com.microservice.product_service.Repositories.CategoryRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

import static com.microservice.product_service.Services.ProductService.getShortProductResponses;

@Service
@Transactional

public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<CategoryResponse> getAllActive(Pageable pageable) {
        return categoryRepository.findByIsActiveTrue(pageable).stream().map(CategoryMapper::mapToCategoryResponse).toList();
    }

    public Long addCategory(CategoryRequest categoryRequest) {
        Category category = Category.builder()
                .categoryName(categoryRequest.getCategoryName())
                .categoryicon(categoryRequest.getCategoryIcon())
                .categoryDescription(categoryRequest.getCategoryDescription())
                .isActive(categoryRequest.getIsActive())
                .createdAt(new Date())
                .updatedAt(new Date())
                .build();
        //.createdBy()
        return categoryRepository.save(category).getCategoryId();
    }

    public List<ShortProductResponse> getCategoryProducts(Long id, boolean forAdmin) {
        return getShortProductResponses(categoryRepository.getReferenceById(id).getProducts(), forAdmin);
    }

    public Long updateCategory(Long id, CategoryRequest categoryRequest) {
        try {
            Category category = categoryRepository.getReferenceById(id);
            if (categoryRequest.getCategoryName() != null) {
                category.setCategoryName(categoryRequest.getCategoryName());
            }
            if (categoryRequest.getCategoryDescription() != null) {
                category.setCategoryDescription(categoryRequest.getCategoryDescription());
            }
            if (categoryRequest.getCategoryIcon() != null) {
                category.setCategoryicon(categoryRequest.getCategoryIcon());
            }
            if (categoryRequest.getIsActive() != null) {
                category.setIsActive(categoryRequest.getIsActive());
            }
            category.setUpdatedAt(new Date());
            //category.setUpdatedAt(new Date());
            return categoryRepository.save(category).getCategoryId();
        } catch (EntityNotFoundException ex) {
            return null;
        }
    }

    public CategoryFullResponse getCategory(Long id) {
        try {
            Category category = categoryRepository.getReferenceById(id);

            return CategoryMapper.mapToCategoryFullResponse(category);
        } catch (EntityNotFoundException ex) {
            return null;
        }
    }

    public Category getFullCategory(Long id) {
        try {
            return categoryRepository.getReferenceById(id);
        } catch (EntityNotFoundException ex) {
            return null;
        }
    }
}
