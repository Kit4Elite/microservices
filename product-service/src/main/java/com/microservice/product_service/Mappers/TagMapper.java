package com.microservice.product_service.Mappers;

import com.microservice.product_service.Dtos.tag.TagFullResponse;
import com.microservice.product_service.Dtos.tag.TagResponse;
import com.microservice.product_service.Models.Tag;

public class TagMapper {
    public static TagResponse mapToTagResponse(Tag tag) {
        return TagResponse.builder()
                .tagId(tag.getTagId())
                .tagName(tag.getTagName())
                .tagIcon(tag.getTagIcon())
                .tagType(tag.getTagName())
                .createdAt(tag.getCreatedAt())
                .updatedAt(tag.getUpdatedAt()).build();
    }
    public static TagFullResponse mapToTagFullResponse(Tag tag) {
        return TagFullResponse.builder().tagId(tag.getTagId())
                .tagName(tag.getTagName())
                .tagIcon(tag.getTagIcon())
                .tagType(tag.getTagName())
                .products(tag.getProducts().stream().map(ProductMapper::mapToShortProductDTO).toList())
                .createdAt(tag.getCreatedAt())
                .updatedAt(tag.getUpdatedAt()).build();
    }
}
