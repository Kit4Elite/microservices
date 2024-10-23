package com.microservice.product_service;

import com.microservice.product_service.Models.Product;
import com.microservice.product_service.Repositories.ProductRepository;
import com.microservice.product_service.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Date;

@SpringBootApplication
public class ProductServiceApplication  implements CommandLineRunner {
    @Autowired
    private ProductService productService;

    public static void main(String[] args) {
        SpringApplication.run(ProductServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("app running");

    }
}
