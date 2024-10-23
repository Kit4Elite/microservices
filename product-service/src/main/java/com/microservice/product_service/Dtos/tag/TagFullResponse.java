package com.microservice.product_service.Dtos.tag;

import com.microservice.product_service.Dtos.ShortProductResponse;
import com.microservice.product_service.Models.Product;
import jakarta.persistence.ManyToMany;
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
public class TagFullResponse {
    private Long tagId;
    private String tagName;
    private String tagIcon;
    private String tagType;
    private List<ShortProductResponse> products;
    private Date createdAt;
    private Date updatedAt;
}
