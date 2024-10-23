package com.microservice.product_service.Dtos.variant;


import lombok.*;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class VariantRequest <T>{
    private String variantName ;
    private String variantDescription ;
    private Set<T> variantValues;
}
