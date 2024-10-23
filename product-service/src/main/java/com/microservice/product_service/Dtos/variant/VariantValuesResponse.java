package com.microservice.product_service.Dtos.variant;

import com.microservice.product_service.Models.Variant;
import com.microservice.product_service.Models.VariantValues;
import lombok.*;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class VariantValuesResponse {
    private Long variantValueId;
    private String value ;
    private Double price;
}
