package com.microservice.product_service.Dtos.attribute;

import com.microservice.product_service.Models.AttributeValues;
import com.microservice.product_service.Models.Product;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class ProductAttributeValuesRequest {
    private Long productId;
    private List<Long> attributeId;
}
