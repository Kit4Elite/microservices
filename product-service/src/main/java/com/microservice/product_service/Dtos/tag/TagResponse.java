package com.microservice.product_service.Dtos.tag;

import com.microservice.product_service.Dtos.ProductResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TagResponse {
    private Long tagId;
    private String tagName;
    private String tagIcon;
    private String tagType;
    private Set<ProductResponse> products ;
    private Date createdAt;
    private Date updatedAt;
}
