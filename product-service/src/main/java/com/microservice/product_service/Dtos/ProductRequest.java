package com.microservice.product_service.Dtos;

import com.microservice.product_service.Models.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductRequest {
    private String productName;
    private String SKU;
    private Double regularPrice;
    private Double discountPrice;
    private String productDescription;
    private Boolean published;
    private List<Long> attributes;
    private List<Long> variants;
    private List<Long> reviews;
    private List<Long> gallery;
    private List<Long> tags;
    private List<Long> categories;
    private Date createdAt;
    private Date updatedAt;
    private String createdBy;
    private String updatedBy;
}
