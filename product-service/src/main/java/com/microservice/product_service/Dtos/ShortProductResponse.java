package com.microservice.product_service.Dtos;

import com.microservice.product_service.Dtos.tag.TagResponse;
import com.microservice.product_service.Models.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShortProductResponse {
    private Long productId;
    private String productName;
    private String SKU ;
    private Double regularPrice;
    private Double discountPrice;
    private Set<Review> reviews = new HashSet<>();
    private String thumbnail;

    private List<TagResponse> tags = new ArrayList<>();
    private List<CategoryResponse> categories = new ArrayList<>();
}
