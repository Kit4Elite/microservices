package com.microservice.product_service.Mappers;

import com.microservice.product_service.Dtos.attribute.AttributeResponse;
import com.microservice.product_service.Dtos.attribute.AttributeValuesResponse;
import com.microservice.product_service.Models.Attribute;
import com.microservice.product_service.Models.AttributeValues;
import com.microservice.product_service.Models.ProductAttributeValues;

import java.util.stream.Collectors;

public class AttributeMapper {
    public static AttributeResponse mapToAttributeResponse(Attribute attribute){
        return AttributeResponse.builder()
                .attributeId(attribute.getAttributeId())
                .attributeName(attribute.getAttributeName())
                .attributeDescription(attribute.getAttributeDescription())
                .attributeValues(attribute.getAttributeValues().stream().map(AttributeMapper::mapToAttributeValuesRequest).collect(Collectors.toSet())).build();
    }
    public static AttributeValuesResponse mapToAttributeValuesRequest(AttributeValues attributeValues){
        return AttributeValuesResponse.builder()
                .attributeValueId(attributeValues.getAttributeValueId())
                .value(attributeValues.getValue())
                .additionalPrice(attributeValues.getAdditionalPrice())
                .build();
    }
    public static AttributeValues mapToAttributeValues(AttributeResponse attributeResponse){
        return AttributeValues.builder()
                .build();
    }
    public static AttributeResponse mapToAttributeValues(ProductAttributeValues productAttributeValues){
        return mapToAttributeResponse(productAttributeValues.getAttribute());
    }
}
