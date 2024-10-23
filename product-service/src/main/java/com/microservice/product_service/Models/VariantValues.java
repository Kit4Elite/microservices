package com.microservice.product_service.Models;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "VariantValues")
@Builder
@Getter
@Setter
public class VariantValues {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long variantValueId;
    private String value ;
    private Double price;
    @ManyToOne
    @JoinColumn(name = "variantId")
    private Variant variant ;

}
