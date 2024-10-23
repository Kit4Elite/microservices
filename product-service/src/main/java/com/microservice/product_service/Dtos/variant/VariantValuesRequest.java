package com.microservice.product_service.Dtos.variant;

import com.microservice.product_service.Models.Variant;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class VariantValuesRequest {
    private String value ;
    private Double price;
}
