package com.microservice.product_service.Dtos.attribute;

import com.microservice.product_service.Models.Attribute;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class AttributeValuesRequest {
    private String value ;
    private Double additionalPrice;
}
