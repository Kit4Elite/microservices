package com.microservice.product_service.Dtos.variant;

import com.microservice.product_service.Models.VariantValues;
import lombok.*;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class VariantResponse {
    private Long variantId;
    private String variantName ;
    private String variantDescription ;
    private Set<VariantValuesResponse> variantValues;
}
