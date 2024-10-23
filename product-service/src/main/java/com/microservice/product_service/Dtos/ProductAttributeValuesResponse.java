package com.microservice.product_service.Dtos;

import com.microservice.product_service.Models.Attribute;
import com.microservice.product_service.Models.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductAttributeValuesResponse {
    private Attribute attribute;
}
