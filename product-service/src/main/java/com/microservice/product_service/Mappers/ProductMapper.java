package com.microservice.product_service.Mappers;

import com.microservice.product_service.Dtos.ProductResponse;
import com.microservice.product_service.Dtos.ShortProductResponse;
import com.microservice.product_service.Models.Product;

public class ProductMapper {
    public static ShortProductResponse mapToShortProductDTO(Product product) {
        if (product != null) {
            ShortProductResponse shortProductDTO = new ShortProductResponse();
            shortProductDTO.setProductId(product.getProductId());
            //shortProductDTO.setSKU(product.getProductName());
            shortProductDTO.setProductName(product.getProductName());
            shortProductDTO.setRegularPrice(product.getRegularPrice());
            shortProductDTO.setDiscountPrice(product.getDiscountPrice());
            if (!product.getTags().isEmpty()) {
                shortProductDTO.setTags(product.getTags().stream().map(TagMapper::mapToTagResponse).toList());
            }
            if (!product.getCategories().isEmpty()) {
                shortProductDTO.setCategories(product.getCategories().stream().map(CategoryMapper::mapToCategoryResponse).toList());
            }
            if (!product.getReviews().isEmpty()) {
                shortProductDTO.setReviews(product.getReviews());
            }
            if (!product.getGallery().isEmpty()) {
                shortProductDTO.setThumbnail(product.getGallery()
                        .stream().findFirst().orElse(null).toString());
            }
            return shortProductDTO;
        }
        return null;
    }
    public static ProductResponse mapToProductResponse(Product product) {
        if (product != null) {
            return ProductResponse.builder().productId(product.getProductId())
                    .productName(product.getProductName())
                    .regularPrice(product.getRegularPrice())
                    .discountPrice(product.getDiscountPrice()).tags(!product.getTags().isEmpty() ? product.getTags().stream().map(TagMapper::mapToTagResponse).toList() : null)
                    .categories(!product.getCategories().isEmpty() ? product.getCategories().stream().map(CategoryMapper::mapToCategoryResponse).toList() : null)
                    .reviews(!product.getReviews().isEmpty() ? product.getReviews().stream().toList() : null)
                    .gallery(!product.getGallery().isEmpty() ? product.getGallery().stream().toList() : null)
                    .productDescription(product.getProductDescription())
                    .SKU(product.getSKU())
                    .attributes(product.getAttributes().stream().map(AttributeMapper::mapToAttributeValues).toList())
                    .variants(product.getVariants().stream().map(VariantMapper::mapToVariantValues).toList())
                    .createdAt(product.getCreatedAt())
                    .updatedAt(product.getUpdatedAt()).build();
        }
        return null;
    }
}
