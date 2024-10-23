package com.microservice.product_service.Dtos.attribute;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class AttributeValuesResponse {
    private Long attributeValueId;
    private String value ;
    private Double additionalPrice;
}
