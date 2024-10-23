package com.microservice.product_service.Controllers;

import com.microservice.product_service.Dtos.ProductRequest;
import com.microservice.product_service.Dtos.ProductResponse;
import com.microservice.product_service.Dtos.ShortProductResponse;
import com.microservice.product_service.Models.Product;
import com.microservice.product_service.Services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Pageable;

import java.util.List;

@RestController
@RequestMapping("api/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("all/short")
    @ResponseStatus(HttpStatus.OK)
    public List<ShortProductResponse> getShortProducts(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return productService.getAllProductsShort(pageable);
    }
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductResponse getProductDetails(@PathVariable Long id, @RequestParam(defaultValue = "0") boolean forAdmin) {
        if (!forAdmin) {
            throw new IllegalStateException("Access denied: Only admins can access this information.");
        }
        return productService.getProduct(id);
    }
    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public Long createProduct(@RequestBody @Valid ProductRequest productRequest) {
        return productService.addProduct(productRequest);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Long updateProduct(@PathVariable Long id,@RequestBody @Valid ProductRequest productRequest) {
        return productService.update(id,productRequest);
    }

}
