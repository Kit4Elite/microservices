package com.microservice.product_service.Dtos;

import com.microservice.product_service.Dtos.attribute.AttributeResponse;
import com.microservice.product_service.Dtos.tag.TagResponse;
import com.microservice.product_service.Dtos.variant.VariantResponse;
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
public class ProductResponse {
    private Long productId;
    private String productName ;
    private String SKU ;
    private Double regularPrice ;
    private Double discountPrice ;
    private String productDescription;
    private List<AttributeResponse> attributes ;
    private List<VariantResponse> variants ;
    private List<Review> reviews ;
    private List<Gallery> gallery ;
    private List<TagResponse> tags ;
    private List<CategoryResponse> categories  ;
    private Date createdAt;
    private Date updatedAt;

}
