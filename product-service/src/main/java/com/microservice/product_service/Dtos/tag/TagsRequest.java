package com.microservice.product_service.Dtos.tag;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TagsRequest {
    private String tagName;
    private String tagIcon;
    private String tagType;
}
