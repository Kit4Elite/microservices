package com.microservice.product_service.Dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryFullResponse {
    private Long categoryId;
    private String categoryName ;
    private String categoryDescription ;
    private String categoryicon;
    private List<ShortProductResponse> products;
    private Date createdAt;
    private Date updatedAt;
}
