package com.microservice.product_service.Controllers;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.microservice.product_service.Dtos.CategoryFullResponse;
import com.microservice.product_service.Dtos.CategoryRequest;
import com.microservice.product_service.Dtos.CategoryResponse;
import com.microservice.product_service.Dtos.ShortProductResponse;
import com.microservice.product_service.Models.Category;
import com.microservice.product_service.Services.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RequestBody;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@JsonAutoDetect
@RequestMapping("api/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @GetMapping("all")
    @ResponseStatus(HttpStatus.OK)
    public List<CategoryResponse> getALL(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return categoryService.getAllActive(pageable);
    }
    @PostMapping(path = "new", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Long createCategory(@RequestBody @Valid CategoryRequest categoryRequest) {
        return categoryService.addCategory(categoryRequest);
    }
    @GetMapping("{id}/products")
    @ResponseStatus(HttpStatus.OK)
    public List<ShortProductResponse> categoryProducts(@PathVariable @Valid Long id, @RequestParam(defaultValue = "0") boolean forAdmin, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return categoryService.getCategoryProducts(id, false);
    }
    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryFullResponse category(@PathVariable @Valid Long id) {
        return categoryService.getCategory(id);
    }
    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public Long update(@PathVariable @Valid Long id, @RequestBody @Valid CategoryRequest categoryRequest) {
        return categoryService.updateCategory(id, categoryRequest);
    }
}
