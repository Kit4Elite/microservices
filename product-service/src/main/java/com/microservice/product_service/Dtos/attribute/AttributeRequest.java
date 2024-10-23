package com.microservice.product_service.Dtos.attribute;

import com.microservice.product_service.Models.AttributeValues;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class AttributeRequest<T> {
    @NotBlank(message = "Name Shouldn't Be Null Or Empty ! ")
    @NotNull(message = "Name Shouldn't Be Null Or Empty ! ")
    @Size(min = 2, message = "Name should be at least 2 caracters ! ")
    private String attributeName;
    private String attributeDescription;
    @Size(min = 1, message = "At least 1 value is needed ! ")
    private Set<T> attributeValues;
}
