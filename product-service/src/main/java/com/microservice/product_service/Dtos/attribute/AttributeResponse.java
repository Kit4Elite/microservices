package com.microservice.product_service.Dtos.attribute;

import lombok.*;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class AttributeResponse {
    private Long attributeId;
    private String attributeName;
    private String attributeDescription;
    private Set<AttributeValuesResponse> attributeValues;
}
