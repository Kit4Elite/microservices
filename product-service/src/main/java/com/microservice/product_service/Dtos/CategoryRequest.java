package com.microservice.product_service.Dtos;

import com.microservice.product_service.Models.Product;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class CategoryRequest {
    @NotBlank(message="Name Shouldn't Be Null Or Empty ! ")
    @NotNull(message="Name Shouldn't Be Null Or Empty ! ")
    @Size(min = 2,message="Name should be at least 2 caracters ! ")
    private String categoryName ;
    private String categoryDescription ;
    private Boolean isActive;
    private String categoryIcon ;


}
