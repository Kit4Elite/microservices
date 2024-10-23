package com.microservice.product_service.Mappers;

import com.microservice.product_service.Dtos.attribute.AttributeResponse;
import com.microservice.product_service.Dtos.attribute.AttributeValuesResponse;
import com.microservice.product_service.Dtos.variant.VariantResponse;
import com.microservice.product_service.Dtos.variant.VariantValuesResponse;
import com.microservice.product_service.Models.*;
import com.microservice.product_service.Models.Variant;

import java.util.stream.Collectors;

public class VariantMapper {
    public static VariantResponse mapToVariantResponse(Variant variant){
        return VariantResponse.builder()
                .variantId(variant.getVariantId())
                .variantName(variant.getVariantName())
                .variantDescription(variant.getVariantDescription())
                .variantValues(variant.getVariantValues().stream().map(VariantMapper::mapToVariantValuesRequest).collect(Collectors.toSet())).build();
    }
    public static VariantValuesResponse mapToVariantValuesRequest(VariantValues variantValues){
        return VariantValuesResponse.builder()
                .variantValueId(variantValues.getVariantValueId())
                .value(variantValues.getValue())
                .price(variantValues.getPrice())
                .build();
    }
    public static VariantResponse mapToVariantValues(ProductVariantValues productVariantValues){
        return mapToVariantResponse(productVariantValues.getVariantValue());
    }
}
